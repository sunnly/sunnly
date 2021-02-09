package wang.sunnly.modules.admin.service;

import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.modules.admin.domain.Role;
import wang.sunnly.modules.admin.mapper.RoleMapper;
import wang.sunnly.mysql.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * RoleService
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface RoleService extends BaseService<RoleMapper, Role> {

    List<String> getDictId(String dictFiled, JwtUserInfo userInfo);

    int insertRole(Role entity);

    List<Map<String, Object>> getRolesByUserId(Long userId);

    int getRoleCount(Long role);

    void validateRole(Long role);

    int getRolesCount(List<Long> roles);

    void validateRoles(List<Long> roles);

    int deleteRole(Role role);

    int updateRole(Role role);
}
