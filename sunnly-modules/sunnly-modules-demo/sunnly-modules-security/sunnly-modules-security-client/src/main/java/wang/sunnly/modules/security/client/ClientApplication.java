package wang.sunnly.modules.security.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import wang.sunnly.security.annotation.EnableClientSecurity;
import wang.sunnly.security.annotation.EnableUserSecurity;

/**
 * ClientApplication
 *
 * @author Sunnly
 * @since 2020/12/23
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//        (basePackages = {
//        "wang.sunnly.security.client.feign",
//        "wang.sunnly.modules.security.client.feign"
//})
@EnableUserSecurity
@EnableClientSecurity
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
