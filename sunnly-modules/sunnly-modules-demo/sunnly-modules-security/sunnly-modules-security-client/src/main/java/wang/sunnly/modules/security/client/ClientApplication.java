package wang.sunnly.modules.security.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import wang.sunnly.security.annotation.EnableMacroUserSecurity;

/**
 * ClientApplication
 *
 * @author Sunnly
 * @since 2020/12/23
 */
@SpringBootApplication
@EnableMacroUserSecurity
@EnableDiscoveryClient
@EnableFeignClients
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
