package wang.sunnly.security.server10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import wang.sunnly.security.annotation.EnableClientSecurity;

/**
 * SecurityServerApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableClientSecurity
public class SecurityServerApplication10 {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServerApplication10.class, args);
    }
}
