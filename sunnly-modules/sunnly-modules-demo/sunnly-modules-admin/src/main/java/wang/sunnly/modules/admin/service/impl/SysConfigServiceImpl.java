package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import wang.sunnly.modules.admin.domain.SysConfig;
import wang.sunnly.modules.admin.mapper.SysConfigMapper;
import wang.sunnly.modules.admin.mapper.SysConfigMapper;
import wang.sunnly.modules.admin.service.SysConfigService;
import wang.sunnly.modules.admin.service.SysConfigService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

@Service
public class SysConfigServiceImpl
        extends BaseServiceImpl<SysConfigMapper, SysConfig>
        implements SysConfigService, BaseService<SysConfigMapper, SysConfig> {


}
