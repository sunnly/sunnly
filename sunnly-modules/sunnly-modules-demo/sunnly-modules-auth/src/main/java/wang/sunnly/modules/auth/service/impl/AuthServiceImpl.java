package wang.sunnly.modules.auth.service.impl;

import wang.sunnly.common.web.exception.enums.CommonResponseEnum;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.api.entity.UserInfo;
import wang.sunnly.modules.auth.exception.AuthAssertEnum;
import wang.sunnly.modules.auth.feign.UserFeign;
import wang.sunnly.modules.auth.service.AuthService;

import java.util.HashMap;
import java.util.Map;

/**
 * AuthServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/11
 */
public class AuthServiceImpl implements AuthService {


    private UserFeign userFeign;

    @Override
    public String login(String username, String password){
        Map<String, String> map = new HashMap<>(2);
        map.put("username",username);
        map.put("password",password);
        ObjectResponse<UserInfo> validate = userFeign.validate(map);
        //断言正常返回
        CommonResponseEnum.SERVER_BUSY.assertNotNull(validate);
        CommonResponseEnum.SERVER_ERROR.assertEquals(validate.getCode(),
                CommonResponseEnum.SUCCESS.getCode());
        UserInfo data = validate.getData();
        AuthAssertEnum.LOGIN_ERROR.assertNotNull(data);


        return null;
    }
}
