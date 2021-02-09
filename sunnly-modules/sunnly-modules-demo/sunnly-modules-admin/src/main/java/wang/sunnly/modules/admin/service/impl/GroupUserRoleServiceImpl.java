package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.common.core.utils.SnowFlake;
import wang.sunnly.modules.admin.domain.GroupUserRole;
import wang.sunnly.modules.admin.mapper.GroupUserRoleMapper;
import wang.sunnly.modules.admin.service.GroupUserRoleService;
import wang.sunnly.modules.admin.service.RoleService;
import wang.sunnly.modules.admin.service.UserService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * GroupUserRoleServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class GroupUserRoleServiceImpl
        extends BaseServiceImpl<GroupUserRoleMapper, GroupUserRole>
        implements GroupUserRoleService, BaseService<GroupUserRoleMapper, GroupUserRole> {

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Override
    public int reallocateRole2User(GroupUserRole groupUserRole) {

        //验证用户角色是否存在
        userService.validateUser(groupUserRole.getUserId());
        roleService.validateRoles(groupUserRole.getRoles());

        //删除用户下所有角色
        mapper.deleteByUserId(groupUserRole.getUserId());
        return addRole2User(groupUserRole);
    }

    @Override
    public int addRole2User(GroupUserRole groupUserRole){

        //验证用户角色是否存在
        userService.validateUser(groupUserRole.getUserId());
        roleService.validateRoles(groupUserRole.getRoles());

        //删除用户下已存在的角色
        mapper.deleteExists(groupUserRole.getUserId(),groupUserRole.getRoles());
        List<GroupUserRole> groupUserRoleList = new ArrayList<>(16);
        List<Long> roles = groupUserRole.getRoles();
        roles.stream().forEach(roleId -> {
            GroupUserRole gur = new GroupUserRole();
            gur.setGroupUserRoleId(snowFlake.nextId());
            gur.setRoleId(roleId);
            gur.setUserId(groupUserRole.getUserId());
            gur.setCreateTime(groupUserRole.getCreateTime());
            gur.setCreateUserId(groupUserRole.getCreateUserId());
            gur.setCreateUserIp(groupUserRole.getCreateUserIp());
            gur.setCreateUserName(groupUserRole.getCreateUserName());
            groupUserRoleList.add(gur);
        });

        return mapper.insertUserRoles(groupUserRoleList);
    }

    @Override
    public int setRole2User(GroupUserRole groupUserRole){
        //验证用户角色是否存在
        userService.validateUser(groupUserRole.getUserId());
        roleService.validateRole(groupUserRole.getRoleId());

        mapper.deleteExist(groupUserRole.getUserId(),groupUserRole.getRoleId());

        groupUserRole.setGroupUserRoleId(snowFlake.nextId());
        return mapper.insert(groupUserRole);
    }

    @Override
    public int deleteRole2User(GroupUserRole groupUserRole){
        int size = 0;
        if (groupUserRole.getRoles() != null && groupUserRole.getRoles().size() > 0){
            size += mapper.deleteExists(groupUserRole.getUserId(), groupUserRole.getRoles());
        }
        if (groupUserRole.getRoleId() != null && groupUserRole.getRoleId() != 0){
            size += mapper.deleteExist(groupUserRole.getUserId(), groupUserRole.getRoleId());
        }
        return size;
    }

}
