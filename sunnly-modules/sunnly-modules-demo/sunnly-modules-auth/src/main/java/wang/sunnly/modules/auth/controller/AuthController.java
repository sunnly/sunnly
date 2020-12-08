package wang.sunnly.modules.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.api.entity.AuthenticationRequest;
import wang.sunnly.modules.auth.feign.UserFeign;

/**
 * UserController
 * 用户
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@RestController
@RequestMapping("/jwt")
public class AuthController {

    private UserFeign userFeign;

    @PostMapping("/token")
    public ObjectResponse<String> createToken(@RequestBody AuthenticationRequest authenticationRequest) {

        return new ObjectResponse<String>().setData("success");
    }
}
