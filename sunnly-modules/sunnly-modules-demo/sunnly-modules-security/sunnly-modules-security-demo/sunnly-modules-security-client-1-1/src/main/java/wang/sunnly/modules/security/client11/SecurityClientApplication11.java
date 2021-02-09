package wang.sunnly.modules.security.client11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import wang.sunnly.security.annotation.EnableAuthSecurity;

/**
 * SecurityClientApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthSecurity
@EnableFeignClients
public class SecurityClientApplication11 {
    public static void main(String[] args) {
        SpringApplication.run(SecurityClientApplication11.class, args);
    }
}
