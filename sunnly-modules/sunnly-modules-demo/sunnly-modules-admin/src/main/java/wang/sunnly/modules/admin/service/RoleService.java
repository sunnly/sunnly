package wang.sunnly.modules.admin.service;

import wang.sunnly.modules.admin.domain.Role;
import wang.sunnly.modules.admin.mapper.RoleMapper;
import wang.sunnly.modules.api.entity.JwtUserInfo;
import wang.sunnly.mysql.service.BaseService;

import java.util.List;

public interface RoleService extends BaseService<RoleMapper, Role> {

    List<String> getDictId(String dictFiled, JwtUserInfo userInfo);
}
