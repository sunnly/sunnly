package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.common.api.exception.UserAssertEnum;
import wang.sunnly.common.core.utils.SnowFlake;
import wang.sunnly.modules.admin.domain.Role;
import wang.sunnly.modules.admin.mapper.RoleMapper;
import wang.sunnly.modules.admin.service.RoleService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;
import wang.sunnly.security.server.api.service.MacroTokenDomainService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * RoleServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class RoleServiceImpl
        extends BaseServiceImpl<RoleMapper, Role>
        implements RoleService, BaseService<RoleMapper, Role> {

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private MacroTokenDomainService macroTokenDomainService;

    @Override
    public List<String> getDictId(String dictFiled, JwtUserInfo userInfo) {
        List<String> list = new ArrayList<>();
        if (userInfo.getUsername().equals("admin")){
            return list;
        }
        list.add("addr");
        list.add("group_id");
        list.add("group_phone");
        list.add("create_time");
        list.add("group_parent_id");
        list.add("fullname");
        list.add("group_fullname");
        return list;
//        return mapper.getDictId(dictFiled);
    }

    @Override
    public int insertRole(Role entity) {
        long roleId = snowFlake.nextId();
        entity.setRoleId(roleId);
        Long roleParentId = entity.getRoleParentId();
        if (roleParentId !=null && roleParentId !=0){
            Role parentRole = mapper.selectByPrimaryKey(roleParentId);
            //断言不为空
            UserAssertEnum.PARENT_ROLE_NOT_EXIST.assertNotNull(parentRole);
            entity.setRoleParentName(parentRole.getRoleName());
            entity.setRoleIds(
                    (parentRole.getRoleIds() == null ? "" : parentRole.getRoleIds().concat(","))
                    .concat(roleId + ""));
        }else {
            entity.setRoleIds(roleId + "");
            entity.setRoleParentId(0L);
            entity.setRoleParentName(null);
        }

        return mapper.insert(entity);
    }


    @Override
    public int deleteRole(Role role){
        return mapper.deleteRole(role);
    }

    @Override
    public List<Map<String, Object>> getRolesByUserId(Long userId) {
        return mapper.getRolesByUserId(userId);
    }

    @Override
    public int getRoleCount(Long roleId) {
        return mapper.getRoleCount(roleId);
    }

    @Override
    public int getRolesCount(List<Long> roleIds) {
        return mapper.getRolesCount(roleIds);
    }

    @Override
    public void validateRole(Long roleId){
        UserAssertEnum.ROLE_ID_NOT_EXIST.assertEquals(getRoleCount(roleId), 1);
    }

    @Override
    public void validateRoles(List<Long> roleIds){
        UserAssertEnum.ROLE_ID_NOT_EXIST.assertEquals(getRolesCount(roleIds), roleIds.size());
    }

    @Override
    public int updateRole(Role role){
//        Role dbRole = mapper.selectByPrimaryKey(role.getRoleId());
//        macroTokenDomainService.copyUpdateInfo(role, dbRole);
//        dbRole.setRoleName(role.getRoleName());
//        dbRole.setRoleCode(role.getRoleCode());

        return mapper.updateRole(role);
    }

}
