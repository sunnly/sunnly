package wang.sunnly.modules.security.client10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import wang.sunnly.security.annotation.EnableClientSecurity;

/**
 * SecurityClientApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableClientSecurity
@EnableFeignClients
public class SecurityClientApplication10 {
    public static void main(String[] args) {
        SpringApplication.run(SecurityClientApplication10.class, args);
    }
}
