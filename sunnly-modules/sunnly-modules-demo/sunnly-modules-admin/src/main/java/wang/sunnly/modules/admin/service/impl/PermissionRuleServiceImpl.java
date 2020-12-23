package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;

import wang.sunnly.modules.admin.domain.PermissionRule;
import wang.sunnly.modules.admin.mapper.PermissionRuleMapper;
import wang.sunnly.modules.admin.service.PermissionRuleService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;
/**
 * PermissionRuleServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class PermissionRuleServiceImpl
        extends BaseServiceImpl<PermissionRuleMapper, PermissionRule>
        implements PermissionRuleService, BaseService<PermissionRuleMapper, PermissionRule> {


}
