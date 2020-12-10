package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import wang.sunnly.modules.admin.domain.Role;
import wang.sunnly.modules.admin.mapper.RoleMapper;
import wang.sunnly.modules.admin.mapper.RoleMapper;
import wang.sunnly.modules.admin.service.RoleService;
import wang.sunnly.modules.admin.service.RoleService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

@Service
public class RoleServiceImpl
        extends BaseServiceImpl<RoleMapper, Role>
        implements RoleService, BaseService<RoleMapper, Role> {


}
