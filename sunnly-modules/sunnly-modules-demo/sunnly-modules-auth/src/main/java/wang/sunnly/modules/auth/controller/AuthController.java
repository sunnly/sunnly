package wang.sunnly.modules.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.auth.entity.User;

import java.util.Map;

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

    @PostMapping("/token")
    public ObjectResponse<String> login(@RequestBody User user){
        return new ObjectResponse<String>().setData("success");
    }
}
