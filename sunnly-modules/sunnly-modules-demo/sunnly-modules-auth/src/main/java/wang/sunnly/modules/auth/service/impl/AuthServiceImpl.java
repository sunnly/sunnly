package wang.sunnly.modules.auth.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import wang.sunnly.common.core.security.jwt.utils.JwtUtil;
import wang.sunnly.common.web.exception.enums.CommonResponseEnum;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.api.entity.JwtUserInfo;
import wang.sunnly.modules.api.entity.UserInfo;
import wang.sunnly.modules.auth.exception.AuthAssertEnum;
import wang.sunnly.modules.auth.feign.UserFeign;
import wang.sunnly.modules.auth.service.AuthService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * AuthServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/11
 */
@Service
public class AuthServiceImpl implements AuthService {


    @Resource
    private UserFeign userFeign;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Value("${macro.user.locked.count:5}")
    private int lockedCount;
    @Value("${macro.user.locked.count:5}")
    private int time;

    @Value("${macro.jwt.user.secret:123456}")
    private String secret;

    @Value("${macro.jwt.user.expired:3600*24}")
    private int expired;


    private static final String VALIDATE_LOCKED_PREFIX_KEY = "validate:locked:";
    private static final String LOCKED_PREFIX_KEY = "locked:";

    @Override
    public long lockedTime(String username){
        return redisTemplate.opsForValue().increment(LOCKED_PREFIX_KEY + username, 0);
    }

    @Override
    public void lockedUser(String username){
        redisTemplate.opsForValue().set(LOCKED_PREFIX_KEY + username, "locked", 5, TimeUnit.MINUTES);
    }

    @Override
    public String login(String username, String password){
        Map<String, String> map = new HashMap<>(2);
        map.put("username",username);
        map.put("password",password);
        ObjectResponse<UserInfo> validate = userFeign.validate(map);
        // 断言正常返回
        CommonResponseEnum.SERVER_BUSY.assertNotNull(validate);

        // 用户名或密码错误
        if (validate.getCode() == AuthAssertEnum.USERNAME_PASSWORD_NOT_MATCH.getCode()){
            String locked = redisTemplate.opsForValue().get(VALIDATE_LOCKED_PREFIX_KEY + username);
            int count = 0;
            if (locked != null){
                count = Integer.parseInt(locked);
            }
            if (count < lockedCount){
                redisTemplate.opsForValue().set(VALIDATE_LOCKED_PREFIX_KEY + username, (++count)+"", 1, TimeUnit.HOURS);
            }else{
                lockedUser(username);
            }
            AuthAssertEnum.USERNAME_PASSWORD_NOT_MATCH.assertFail();
        }
        // 验证未成功，包括网络调用异常问题
        CommonResponseEnum.SERVER_ERROR.assertEquals(validate.getCode(),
                CommonResponseEnum.SUCCESS.getCode());
        // 用户登录成功
        UserInfo userInfo = validate.getData();
        AuthAssertEnum.LOGIN_ERROR.assertNotNull(userInfo);

        // jwt token生成
        JwtUserInfo jwtUserInfo= new JwtUserInfo();
        BeanUtils.copyProperties(userInfo, jwtUserInfo);
        JwtUtil jwtUtil = new JwtUtil();
        try {
            return jwtUtil.genJwt(jwtUserInfo, secret, expired);
        } catch (Exception e) {
            AuthAssertEnum.LOGIN_ERROR.assertFail();
            return null;
        }
    }
}
