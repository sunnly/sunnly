package wang.sunnly.modules.nacos.support.service.impl;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.sunnly.nacos.support.monitor.constant.NacosOnlineConstant;
import wang.sunnly.nacos.support.monitor.service.NacosMonitorService;

import java.util.List;

/**
 * NacosMonitorServiceImpl
 *
 * @author Sunnly
 * @since 2020/11/23 18:15
 */
@Service
public class NacosMonitorServiceImpl implements NacosMonitorService {

    @Autowired
    private NamingService namingService;

    @Override
    public void monitor(List<Instance> instances, String serviceName, NacosOnlineConstant serviceOnline) {

        try {
            List<Instance> instances1 = namingService.selectInstances(serviceName, true);

            if (instances1.size() == 0){
                System.out.println("无服务");
            }else {
                System.out.println("别担心，只是集群数发生变化，当前服务还是可以的，可用数量:"+instances1.size());
            }


        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

}
