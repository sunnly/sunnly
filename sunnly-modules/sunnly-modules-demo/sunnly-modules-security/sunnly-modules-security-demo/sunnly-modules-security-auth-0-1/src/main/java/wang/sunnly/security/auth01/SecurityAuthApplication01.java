package wang.sunnly.security.auth01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import wang.sunnly.security.annotation.EnableAuthUserSecurity;

/**
 * SecurityAuthApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthUserSecurity
@EnableFeignClients
public class SecurityAuthApplication01 {
    public static void main(String[] args) {
        SpringApplication.run(SecurityAuthApplication01.class, args);
    }
}
