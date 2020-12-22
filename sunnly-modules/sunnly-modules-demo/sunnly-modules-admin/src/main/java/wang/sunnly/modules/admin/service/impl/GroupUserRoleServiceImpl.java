package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.modules.admin.domain.GroupConfig;
import wang.sunnly.modules.admin.mapper.GroupConfigMapper;
import wang.sunnly.modules.admin.service.GroupConfigService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;
/**
 * GroupUserRoleServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class GroupUserRoleServiceImpl
        extends BaseServiceImpl<GroupConfigMapper, GroupConfig>
        implements GroupConfigService, BaseService<GroupConfigMapper, GroupConfig> {


}
