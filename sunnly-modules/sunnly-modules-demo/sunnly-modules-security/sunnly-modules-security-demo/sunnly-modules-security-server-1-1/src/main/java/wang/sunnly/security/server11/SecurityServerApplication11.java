package wang.sunnly.security.server11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import wang.sunnly.security.annotation.EnableAuthSecurity;

/**
 * SecurityServerApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthSecurity
public class SecurityServerApplication11 {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServerApplication11.class, args);
    }
}
