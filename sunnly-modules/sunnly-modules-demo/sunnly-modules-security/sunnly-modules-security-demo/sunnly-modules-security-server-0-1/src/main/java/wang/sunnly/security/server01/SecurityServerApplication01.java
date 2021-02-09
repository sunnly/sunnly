package wang.sunnly.security.server01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import wang.sunnly.security.annotation.EnableUserSecurity;

/**
 * SecurityServerApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableUserSecurity
public class SecurityServerApplication01 {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServerApplication01.class, args);
    }
}
