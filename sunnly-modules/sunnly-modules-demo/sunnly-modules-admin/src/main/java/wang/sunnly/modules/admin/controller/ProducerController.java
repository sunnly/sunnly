package wang.sunnly.modules.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.api.entity.UserInfo;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.service.UserService;
import wang.sunnly.security.ignore.annotation.IgnoreUserToken;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * ProducerController
 * 提供第三方服务访问的接口
 *
 * @author Sunnly
 * @since 2021/1/28
 */
@RestController
@RequestMapping("producer")
public class ProducerController {

    @Resource
    private UserService userService;

    // ***********************************第三方服务调用****************************************
    // 网关获取用户权限
    /**
     * 验证用户，供鉴权服务器调用
     * @param authInfo
     * @param request
     * @return
     */
    @PostMapping("user/validate")
    @IgnoreUserToken
    public ObjectResponse<UserInfo> validate(@RequestBody Map<String, String> authInfo, HttpServletRequest request) {
        //用户查询验证，返回用户信息
        return new ObjectResponse<>(userService.validate(request,authInfo.get("username"), authInfo.get("password")));
    }
}
