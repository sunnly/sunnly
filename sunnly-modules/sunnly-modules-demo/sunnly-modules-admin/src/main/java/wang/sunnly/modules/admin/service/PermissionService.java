package wang.sunnly.modules.admin.service;

import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.modules.admin.domain.Permission;
import wang.sunnly.modules.admin.mapper.PermissionMapper;
import wang.sunnly.mysql.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * PermissionService
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface PermissionService extends BaseService<PermissionMapper, Permission> {

    List<List<String>> getColumn(JwtUserInfo userInfo, String permissionCode);

    Map<String, String> getRowRule(JwtUserInfo userInfo, String permissionCode);

}
