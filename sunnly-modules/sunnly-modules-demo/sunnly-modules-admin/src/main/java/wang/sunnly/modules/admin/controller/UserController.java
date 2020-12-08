package wang.sunnly.modules.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * UserController
 * 用户信息获取
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("front/info")
    public ObjectResponse<UserInfo> getInfo(){

        UserInfo userInfo = new UserInfo();
        List<Map<String,Object>> list = new ArrayList<>();

        return new ObjectResponse<UserInfo>(userInfo);
    }
}
