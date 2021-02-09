package wang.sunnly.modules.admin.controller;

import org.springframework.web.bind.annotation.*;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.domain.Role;
import wang.sunnly.modules.admin.service.RoleService;
import wang.sunnly.mysql.controller.BaseController;
import wang.sunnly.security.ignore.annotation.IgnoreClientToken;
import wang.sunnly.security.server.api.service.MacroTokenDomainService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * RoleController
 * 角色管理
 *
 * @author Sunnly
 * @since 2021/1/25
 */
@RestController
@RequestMapping("role")
@IgnoreClientToken
public class RoleController extends BaseController<RoleService, Role> {

    @Resource
    private MacroTokenDomainService macroTokenDomainService;

    /**
     * 添加角色
     * @param entity 角色实体
     * @param request 请求体
     * @return 返回添加的角色
     */
    @PostMapping
    public ObjectResponse<Role> addRole(@RequestBody Role entity, HttpServletRequest request){
        macroTokenDomainService.setCreateInfo(request, entity);
        return new ObjectResponse<Role>(service.insertRole(entity) == 1 ? entity : null);
    }

    /**
     * 删除角色
     * @param roleId 角色ID
     * @param request 请求体
     * @return 返回是否删除成功
     */
    @DeleteMapping("{roleId}")
    public ObjectResponse<Boolean> deleteRole(@PathVariable("roleId") Long roleId,
                                              HttpServletRequest request){
        Role role = service.selectById(roleId);
        macroTokenDomainService.setUpdateInfo(request, role);
        return new ObjectResponse<>(service.deleteRole(role) == 1);
    }

    /**
     * 修改角色
     * @param role 角色
     * @param request 请求
     * @return 返回修改是否成功
     */
    @PutMapping
    public ObjectResponse<Boolean> updateRole(@RequestBody Role role, HttpServletRequest request){
        macroTokenDomainService.setUpdateInfo(request, role);
        return new ObjectResponse<>(service.updateRole(role) == 1);
    }
}
