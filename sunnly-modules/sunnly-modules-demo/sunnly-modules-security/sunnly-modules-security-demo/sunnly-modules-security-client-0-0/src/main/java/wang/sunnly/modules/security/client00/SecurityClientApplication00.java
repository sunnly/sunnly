package wang.sunnly.modules.security.client00;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SecurityClientApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SecurityClientApplication00 {
    public static void main(String[] args) {
        SpringApplication.run(SecurityClientApplication00.class, args);
    }
}
