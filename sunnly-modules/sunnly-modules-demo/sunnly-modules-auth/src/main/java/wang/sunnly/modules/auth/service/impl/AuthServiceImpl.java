package wang.sunnly.modules.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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

    @Override
    public long lockedTime(String username){
        return redisTemplate.opsForValue().increment("locked:" + username, 0);
//        return redisTemplate.boundValueOps("locked:"+username).getExpire();
    }

    @Override
    public void lockedUser(String username){
        redisTemplate.opsForValue().set("locked:"+username, "locked", 5, TimeUnit.MINUTES);
//        redisTemplate.boundValueOps("locked:"+username).set("locked",  5, TimeUnit.MINUTES);
    }

    @Override
    public void setValidateCode(String username, String code){
        redisTemplate.opsForValue().set("validate:code:" + username, code,5, TimeUnit.MINUTES);
//        redisTemplate.boundValueOps("validate:code:" + username).set(code, 5, TimeUnit.MINUTES);
    }
    @Override
    public String getValidateCode(String username){
        return redisTemplate.opsForValue().get("validate:code:" + username);
//        return redisTemplate.boundValueOps("validate:code:" + username).get();
    }

    @Override
    public void removeValidateCode(String username){
        redisTemplate.opsForValue().set("validate:code:" + username, "", 1, TimeUnit.SECONDS);
//        redisTemplate.boundValueOps("validate:code:" + username).expire(1, TimeUnit.SECONDS);
    }

    @Override
    public String login(String username, String password){
        Map<String, String> map = new HashMap<>(2);
        map.put("username",username);
        map.put("password",password);
        ObjectResponse<UserInfo> validate = userFeign.validate(map);
        //断言正常返回
        CommonResponseEnum.SERVER_BUSY.assertNotNull(validate);
        if (validate.getCode() == AuthAssertEnum.USERNAME_PASSWORD_NOT_MATCH.getCode()){
            //用户名或密码错误
            String lockedCount = redisTemplate.opsForValue().get("validate:locked:" + username);
//            String lockedCount = redisTemplate.boundValueOps("validate:locked:" + username).get();
            int count = 0;
            if (lockedCount != null){
                count = Integer.parseInt(lockedCount);
            }
            if (count < 5){
                redisTemplate.opsForValue().set("validate:locked:"+username, (++count)+"", 1, TimeUnit.HOURS);
//                redisTemplate.boundValueOps("validate:locked:"+username).set((++count)+"", 1, TimeUnit.HOURS);
            }else{
                lockedUser(username);
            }
            AuthAssertEnum.USERNAME_PASSWORD_NOT_MATCH.assertFail();
        }
        CommonResponseEnum.SERVER_ERROR.assertEquals(validate.getCode(),
                CommonResponseEnum.SUCCESS.getCode());
        UserInfo userInfo = validate.getData();
        AuthAssertEnum.LOGIN_ERROR.assertNotNull(userInfo);

//        jwt token生成
        JwtUserInfo jwtUserInfo= new JwtUserInfo();
        BeanUtils.copyProperties(userInfo, jwtUserInfo);
        JwtUtil jwtUtil = new JwtUtil();
        try {
            return jwtUtil.genJwt(jwtUserInfo, "123456");
        } catch (Exception e) {
            AuthAssertEnum.LOGIN_ERROR.assertFail();
            return null;
        }
    }
}
