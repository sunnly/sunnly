package wang.sunnly.security.auth00;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SecurityAuthApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SecurityAuthApplication00 {
    public static void main(String[] args) {
        SpringApplication.run(SecurityAuthApplication00.class, args);
    }
}
