package wang.sunnly.modules.admin.controller;

import org.springframework.web.bind.annotation.*;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.modules.admin.service.UserService;
import wang.sunnly.modules.api.entity.AuthenticationRequest;
import wang.sunnly.modules.api.entity.FrontUserInfo;
import wang.sunnly.modules.api.entity.UserInfo;
import wang.sunnly.mysql.controller.BaseController;

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
public class UserController extends BaseController<UserService, User> {

    @PostMapping("validate")
    public ObjectResponse<UserInfo> validate(@RequestBody AuthenticationRequest authInfo) {
        //用户查询验证，返回用户信息
        return new ObjectResponse<>(service.validate(authInfo.getUsername(), authInfo.getPassword()));
    }

    @GetMapping("front/info")
    public ObjectResponse<FrontUserInfo> getInfo() {

        FrontUserInfo userInfo = new FrontUserInfo();
        List<Map<String, Object>> list = new ArrayList<>();

        return new ObjectResponse<FrontUserInfo>(userInfo);
    }

    //获取用户权限
    //获取所有权限
    //获取用户菜单
    //获取所有用户菜单
}
