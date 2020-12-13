package wang.sunnly.modules.auth.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.auth.domain.SystemConfig;
import wang.sunnly.modules.auth.service.SystemConfigService;
import wang.sunnly.mysql.controller.BaseController;

/**
 * SystemController
 *
 * @author Sunnly
 * @since 2020/12/11 0011
 */
@RestController
@RequestMapping("sys")
public class SystemConfigController extends BaseController<SystemConfigService, SystemConfig> {

    @RequestMapping("{channel}")
    public ObjectResponse<SystemConfig> get(@PathVariable String channel) {
        return new ObjectResponse<>(service.getSysConfigByChannel(channel));
    }
}
