package wang.sunnly.modules.auth.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wang.sunnly.common.api.entity.UserInfo;
import wang.sunnly.common.web.exception.enums.CommonResponseEnum;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.auth.feign.UserFeign;
import wang.sunnly.modules.auth.service.AuthService;
import wang.sunnly.redis.utils.RedisOpsForValue;
import wang.sunnly.security.exception.AuthAssertEnum;
import wang.sunnly.security.server.api.service.MacroTokenDomainService;

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
    private RedisOpsForValue<String,String> redisOpsForValue;

    @Resource
    private MacroTokenDomainService macroTokenDomainService;

    @Value("${macro.user.locked.count:5}")
    private int lockedCount;
    @Value("${macro.user.locked.timeout:5}")
    private int timeout;

    @Value("${macro.security.user.secret:123456}")
    private String secret;

    @Value("${macro.security.user.expired:86400}")
    private int expired;


    private static final String VALIDATE_LOCKED_PREFIX_KEY = "validate:locked:";
    private static final String LOCKED_PREFIX_KEY = "locked:";

    @Override
    public long lockedTime(String username){
        return redisOpsForValue.getExpire(LOCKED_PREFIX_KEY + username);
    }

    @Override
    public void lockedUser(String username){
        redisOpsForValue.set(LOCKED_PREFIX_KEY + username, "locked", timeout, TimeUnit.MINUTES);
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
            String locked = redisOpsForValue.get(VALIDATE_LOCKED_PREFIX_KEY + username);
            int count = 0;
            if (locked != null){
                count = Integer.parseInt(locked);
            }
            if (count < lockedCount){
                redisOpsForValue.set(VALIDATE_LOCKED_PREFIX_KEY + username, (++count)+"", 1, TimeUnit.HOURS);
            }else{
                lockedUser(username);
                redisOpsForValue.delete(VALIDATE_LOCKED_PREFIX_KEY + username);
            }
            AuthAssertEnum.USERNAME_PASSWORD_NOT_MATCH.assertFail(count,lockedCount-count);
        }
        // 验证未成功，包括网络调用异常问题
        CommonResponseEnum.SERVER_ERROR.assertEquals(validate.getCode(),
                CommonResponseEnum.SUCCESS.getCode());
        // 用户登录成功
        UserInfo userInfo = validate.getData();
        AuthAssertEnum.LOGIN_ERROR.assertNotNull(userInfo);
        // 登录成功清除登录失败此次
        redisOpsForValue.delete(VALIDATE_LOCKED_PREFIX_KEY + username);

        // jwt token生成
        return macroTokenDomainService.genUserToken(userInfo);
    }
}
