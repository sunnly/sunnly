package wang.sunnly.modules.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.modules.admin.service.GroupService;
import wang.sunnly.mysql.controller.BaseController;
import wang.sunnly.mysql.utils.MacroDomainUtils;
import wang.sunnly.security.server.service.MacroDomainService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * GroupController
 * 机构管理
 *
 * @author Sunnly
 * @since 2020/12/10 0010
 */
@RestController
@RequestMapping("group")
@Slf4j
public class GroupController extends BaseController<GroupService, Group> {

    // TODO 解析token需重新调整为rsa的
//    @Value("${macro.security.user.token-header}")
//    private String tokenHeader;
//    @Value("${macro.security.user.rsa-secret}")
//    private String secret;

    @Resource
    private MacroDomainService macroDomainService;

    @PostMapping("")
    public ObjectResponse<Group> addGroup(@RequestBody Group entity, HttpServletRequest request) {
//        String token = request.getHeader(tokenHeader);
//
//        // 获取jwt中的用户信息
//        UserAssertEnum.USER_TOKEN_NOT_EMPTY.assertNotNull(token);
//
//        JwtUserInfo jwtInfo = null;
//
//        JwtUtil jwtUtil = new JwtUtil();
//        jwtInfo = (JwtUserInfo) jwtUtil.getJwtInfo(JwtUserInfo.class, token, secret);
//
//        ArgumentResponseEnum.TOKEN_NOT_NULL.assertNotNull(jwtInfo);
//        String userId = jwtInfo.getId();
//        String username = jwtInfo.getUsername();
//        String loginIp = jwtInfo.getLoginIp();
//        String loginTime = jwtInfo.getLoginTime()
        macroDomainService.setCreateInfo(request, entity);


        return new ObjectResponse<Group>(service.insertGroup(entity) == 1 ? entity : null);
    }

    @GetMapping("/children/{parentId}")
    public ListResponse<Group> getChildren(@PathVariable("parentId") long parentId,
                                           @RequestParam(value = "exclude", defaultValue = "0") int exclude) {
        return new ListResponse<>(service.getChildren(parentId, exclude));
    }

    @GetMapping("/children1/{parentId}")
    public ListResponse<Map<String, Object>> getChildren1(@PathVariable("parentId") long parentId,
                                                          @RequestParam(value = "exclude", defaultValue = "0") int exclude) {
        List<Map<String, Object>> children = service.query(parentId, exclude);
//        TreeNodeUtils<Long, Group> treeNodeUtils = new TreeNodeUtils<Long, Group>()
//        return new ObjectResponse<>(treeNodeUtils.parseTreeNode(children, parentId))
        return new ListResponse<>(children);
    }


}
