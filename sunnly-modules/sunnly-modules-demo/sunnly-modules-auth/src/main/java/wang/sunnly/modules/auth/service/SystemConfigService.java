package wang.sunnly.modules.auth.service;

import wang.sunnly.modules.auth.domain.SystemConfig;
import wang.sunnly.modules.auth.mapper.SystemConfigMapper;
import wang.sunnly.mysql.service.BaseService;

/**
 * SystemConfigService
 *
 * @author Sunnly
 * @since 2020/12/11
 */
public interface SystemConfigService extends BaseService<SystemConfigMapper, SystemConfig> {

    SystemConfig getSysConfigByChannel(String channel);
}

