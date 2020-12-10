package wang.sunnly.modules.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.core.security.jwt.utils.JwtUtil;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.modules.admin.exception.UserAssertEnum;
import wang.sunnly.modules.admin.service.GroupService;
import wang.sunnly.mysql.controller.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * GroupController
 * 机构管理
 *
 * @author Sunnly
 * @since 2020/12/10 0010
 */
@RestController
@RequestMapping("group")
public class GroupController extends BaseController<GroupService, Group> {

    @PostMapping("")
    public ObjectResponse<Group> addGroup(@RequestBody Group entity, HttpServletRequest request){
        String token = request.getHeader("Auth");
//        TODO 获取jwt中的用户信息
//        UserAssertEnum.USER_TOKEN_NOT_EMPTY.assertNotNull(token);
        Long userId = 1001L;
        String userName = "admin";
        String userIp = "192.168.0.1";
        return new ObjectResponse<Group>(service.insertGroup(entity, userId, userName, userIp)==1 ? entity : null);
    }
}
