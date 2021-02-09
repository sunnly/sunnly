package wang.sunnly.modules.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import wang.sunnly.modules.gateway.annotation.EnableGateWayCors;
import wang.sunnly.security.annotation.EnableGateWayClientSecurity;
import wang.sunnly.security.annotation.EnableGateWaySecurity;
import wang.sunnly.security.annotation.EnableGateWayUserSecurity;


/**
 * GateWayApplication
 * 网关入口
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGateWayCors
@EnableFeignClients
@EnableGateWaySecurity
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
