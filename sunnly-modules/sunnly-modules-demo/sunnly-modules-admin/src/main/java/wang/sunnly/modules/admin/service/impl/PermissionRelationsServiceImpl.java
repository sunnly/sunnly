package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.modules.admin.domain.PermissionRelations;
import wang.sunnly.modules.admin.mapper.PermissionRelationsMapper;
import wang.sunnly.modules.admin.service.PermissionRelationsService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;
/**
 * PermissionRelationsServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class PermissionRelationsServiceImpl
        extends BaseServiceImpl<PermissionRelationsMapper, PermissionRelations>
        implements PermissionRelationsService, BaseService<PermissionRelationsMapper, PermissionRelations> {


}
