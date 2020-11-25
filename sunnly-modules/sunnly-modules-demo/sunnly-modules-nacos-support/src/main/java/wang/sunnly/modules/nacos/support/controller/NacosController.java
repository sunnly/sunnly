package wang.sunnly.modules.nacos.support.controller;

import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.sunnly.nacos.support.monitor.service.NacosRegistrateService;
import wang.sunnly.nacos.support.nacosconfig.service.MacroNacosConfigService;

import java.util.List;

/**
 * NacosController
 *
 * @author Sunnly
 * @since 2020/11/24 16:10
 */
@RestController
@RequestMapping("nacos")
public class NacosController {

    @Autowired
    private NacosRegistrateService nacosRegistrateService;

    @Autowired
    private MacroNacosConfigService macroNacosConfigService;

    @PostMapping("filter/online")
    public List<String> getOnlineService(@RequestBody List<String> list) {
        return nacosRegistrateService.filterOnlineService(list);
    }

    @GetMapping("config/{dataId}/{group}")
    public String getConfig(@PathVariable("dataId") String dataId,@PathVariable("group") String group) throws NacosException {
        return macroNacosConfigService.getConfig(dataId, group);
    }

    @DeleteMapping("config/{dataId}/{group}")
    public boolean removeConfig(@PathVariable("dataId") String dataId,@PathVariable("group") String group) throws NacosException {
        return macroNacosConfigService.removeConfig(dataId, group);
    }

    @PostMapping("config/{dataId}/{group}")
    public boolean publishConfig(@PathVariable("dataId") String dataId,
                                 @PathVariable("group") String group,
                                 @RequestBody String content) throws NacosException{
        return macroNacosConfigService.publishConfig(dataId,group,content);
    }

    @GetMapping("listener/{dataId}/{group}")
    public String addConfigListener(@PathVariable("dataId") String dataId,@PathVariable("group") String group) throws NacosException {
        macroNacosConfigService.addConfigListener(dataId, group);
        return "add success";
    }

    @DeleteMapping("listener/{dataId}/{group}")
    public String deleteConfigListener(@PathVariable("dataId") String dataId,@PathVariable("group") String group) throws NacosException {
        macroNacosConfigService.removeConfigListener(dataId, group);
        return "remove success";
    }
}
