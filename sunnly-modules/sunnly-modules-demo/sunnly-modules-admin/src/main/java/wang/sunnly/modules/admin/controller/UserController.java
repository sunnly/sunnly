package wang.sunnly.modules.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.api.entity.AuthenticationRequest;
import wang.sunnly.modules.api.entity.FrontUserInfo;
import wang.sunnly.modules.api.entity.UserInfo;

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

    @PostMapping("validate")
    public UserInfo validate(AuthenticationRequest authInfo){
        String username = authInfo.getUsername();
        //用户查询验证，返回用户信息

        UserInfo userInfo = new UserInfo();

        return null;
    }

    @GetMapping("front/info")
    public ObjectResponse<FrontUserInfo> getInfo(){

        FrontUserInfo userInfo = new FrontUserInfo();
        List<Map<String,Object>> list = new ArrayList<>();

        return new ObjectResponse<FrontUserInfo>(userInfo);
    }

    //获取用户权限
    //获取所有权限
    //获取用户菜单
    //获取所有用户菜单
}
