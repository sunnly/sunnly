package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.modules.admin.domain.ResourcesRole;
import wang.sunnly.modules.admin.mapper.ResourcesRoleMapper;
import wang.sunnly.modules.admin.service.ResourcesRoleService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;
/**
 * ResourcesRoleServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class ResourcesRoleServiceImpl
        extends BaseServiceImpl<ResourcesRoleMapper, ResourcesRole>
        implements ResourcesRoleService, BaseService<ResourcesRoleMapper, ResourcesRole> {


}
