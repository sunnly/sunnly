package wang.sunnly.modules.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.api.entity.AuthenticationRequest;
import wang.sunnly.modules.auth.exception.AuthAssertEnum;
import wang.sunnly.mysql.controller.BaseController;

/**
 * UserController
 * 用户
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@RestController
@RequestMapping("/jwt")
public class AuthController  extends BaseController {

    @PostMapping("/token")
    public ObjectResponse<String> createToken(@RequestBody AuthenticationRequest authenticationRequest) {

        //断言账户锁定状态
        AuthAssertEnum.ACCOUT_LOCK.assertIsTrue(true);
        //验证码判断

        //账户验证

//        service.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        return new ObjectResponse<String>().setData("success");
    }
}
