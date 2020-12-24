package wang.sunnly.modules.auth.service.impl;

import org.springframework.stereotype.Service;

import wang.sunnly.modules.auth.domain.SystemConfig;
import wang.sunnly.modules.auth.mapper.SystemConfigMapper;
import wang.sunnly.modules.auth.service.SystemConfigService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;
/**
 * SystemConfigServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/11
 */
@Service
public class SystemConfigServiceImpl
        extends BaseServiceImpl<SystemConfigMapper, SystemConfig>
        implements SystemConfigService, BaseService<SystemConfigMapper, SystemConfig> {

    @Override
    public SystemConfig getSysConfigByChannel(String channel){
        return mapper.getSysConfigByChannel(channel);
    }

}

