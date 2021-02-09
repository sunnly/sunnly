package wang.sunnly.modules.admin.service;

import wang.sunnly.modules.admin.domain.GroupUserRole;
import wang.sunnly.modules.admin.mapper.GroupUserRoleMapper;
import wang.sunnly.mysql.service.BaseService;

/**
 * GroupUserRoleService
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface GroupUserRoleService extends BaseService<GroupUserRoleMapper, GroupUserRole> {

    /**
     * 用户关联角色，删除原有的角色重新分配
     * @param groupUserRole 用户角色
     * @return 影响的条数
     */
    int reallocateRole2User(GroupUserRole groupUserRole);

    /**
     * 用户关联角色，保持原有的角色
     * @param groupUserRole 用户角色
     * @return 影响的条数
     */
    int addRole2User(GroupUserRole groupUserRole);

    /**
     * 用户关联角色
     * @param groupUserRole 用户角色
     * @return 影响的条数
     */
    int setRole2User(GroupUserRole groupUserRole);

    /**
     * 用户删除角色
     * @param groupUserRole
     * @return
     */
    int deleteRole2User(GroupUserRole groupUserRole);
}
