package wang.sunnly.modules.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import wang.sunnly.common.api.exception.UserAssertEnum;
import wang.sunnly.common.core.utils.TreeNodeUtils;
import wang.sunnly.common.core.utils.tree.BaseTreeNode;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.modules.admin.domain.GroupUser;
import wang.sunnly.modules.admin.service.GroupService;
import wang.sunnly.modules.admin.service.GroupUserService;
import wang.sunnly.mysql.controller.BaseController;
import wang.sunnly.security.ignore.annotation.IgnoreClientToken;
import wang.sunnly.security.server.api.service.MacroTokenDomainService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * GroupController
 * 机构部门管理
 *
 * @author Sunnly
 * @since 2020/12/10 0010
 */
@RestController
@RequestMapping("group")
@Slf4j
@IgnoreClientToken
public class GroupController extends BaseController<GroupService, Group> {

    @Resource
    private MacroTokenDomainService macroTokenDomainService;

    @Resource
    private GroupUserService groupUserService;

    // *********************************** 【管理本部门用户】****************************************
    /**
     * 部门新增用户
     *
     * @param groupUser 机构用户
     * @return 返回是否添加成功
     */
    @PostMapping("/user")
    @IgnoreClientToken
    public ObjectResponse<Boolean> addDeptForUser(@RequestBody GroupUser groupUser){
        return new ObjectResponse<>(groupUserService.insertGroupUsers(groupUser) > 0);
    }
    /**
     * 部门删除用户
     *
     * @param groupUser 机构用户
     * @return 返回是否添加成功
     */
    @DeleteMapping("/user")
    @IgnoreClientToken
    public ObjectResponse<Boolean> deleteDeptForUser(@RequestBody GroupUser groupUser){
        return new ObjectResponse<>(groupUserService.deleteGroupUsers(groupUser) > 0);
    }
    // TODO *********************************** 【管理本部门/本机构角色】*************************


    // ***********************************机构/部门管理****************************************
    /**
     * 新增部门、机构
     * @param entity
     * @param request
     * @return
     */
    @PostMapping
    public ObjectResponse<Group> addGroup(@RequestBody Group entity, HttpServletRequest request) {
        macroTokenDomainService.setCreateInfo(request, entity);
        return new ObjectResponse<Group>(service.insertGroup(entity) == 1 ? entity : null);
    }

    /**
     * 删除部门、机构
     * 删除机构部门需要判断该机构部门下无子机构部门，无用户
     * @param groupId 机构ID
     * @param request 请求
     * @return 返回是否删除成功
     */
    @DeleteMapping("{groupId}")
    public ObjectResponse<Boolean> deleteGroup(@PathVariable("groupId") Long groupId,
                                               @RequestParam(value = "cascading",defaultValue = "false") boolean cascading,HttpServletRequest request){
        Group group = service.selectById(groupId);
        UserAssertEnum.GROUP_ID_NOT_EXIST.assertNotNull(group);
        macroTokenDomainService.setUpdateInfo(request, group);
        return new ObjectResponse<>(service.deleteGroup(group, cascading) == 1);
    }

    /**
     * 批量删除部门、机构
     * @param request 请求
     * @return 返回是否删除成功
     */
    @DeleteMapping
    public ObjectResponse<Boolean> deleteGroups(@RequestBody Group group, HttpServletRequest request){
        macroTokenDomainService.setUpdateInfo(request, group);
        return new ObjectResponse<>(service.deleteGroups(group) > 0);
    }

    /**
     * 修改部门、机构
     * @param entity 机构、部门实体类
     * @param request 请求
     * @return 返回修改后的机构、部门
     */
    @PutMapping
    public ObjectResponse<Group> updateGroup(@RequestBody Group entity, HttpServletRequest request){
        macroTokenDomainService.setUpdateInfo(request,entity);
        return new ObjectResponse<>(service.updateGroup(entity) == 1 ? entity : null);
    }


    @GetMapping("/children/{parentId}")
    public ListResponse<Group> getChildren(@PathVariable("parentId") long parentId,
                                           @RequestParam(value = "exclude", defaultValue = "0") int exclude) {
        return new ListResponse<>(service.getChildren(parentId, exclude));
    }

    @GetMapping("/children/tree/{parentId}")
    public ObjectResponse<BaseTreeNode<Long>> getChildren1(@PathVariable("parentId") long parentId) {

        List<Group> children = service.getChildren(parentId, 1);
        TreeNodeUtils<Long, Group> treeNodeUtils = new TreeNodeUtils<>();
        BaseTreeNode<Long> longBaseTreeNode = treeNodeUtils.parseTreeNode(children, parentId);
        return new ObjectResponse<>(longBaseTreeNode);
    }


}
