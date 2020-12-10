package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import wang.sunnly.modules.admin.domain.ResourcesRole;
import wang.sunnly.modules.admin.mapper.ResourcesRoleMapper;
import wang.sunnly.modules.admin.mapper.ResourcesRoleMapper;
import wang.sunnly.modules.admin.service.ResourcesRoleService;
import wang.sunnly.modules.admin.service.ResourcesRoleService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

@Service
public class ResourcesRoleServiceImpl
        extends BaseServiceImpl<ResourcesRoleMapper, ResourcesRole>
        implements ResourcesRoleService, BaseService<ResourcesRoleMapper, ResourcesRole> {


}
