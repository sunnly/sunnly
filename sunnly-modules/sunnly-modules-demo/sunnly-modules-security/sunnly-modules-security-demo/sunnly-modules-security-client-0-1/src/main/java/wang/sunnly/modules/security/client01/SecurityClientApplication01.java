package wang.sunnly.modules.security.client01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import wang.sunnly.security.annotation.EnableUserSecurity;

/**
 * SecurityClientApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableUserSecurity
@EnableFeignClients
public class SecurityClientApplication01 {
    public static void main(String[] args) {
        SpringApplication.run(SecurityClientApplication01.class, args);
    }
}
