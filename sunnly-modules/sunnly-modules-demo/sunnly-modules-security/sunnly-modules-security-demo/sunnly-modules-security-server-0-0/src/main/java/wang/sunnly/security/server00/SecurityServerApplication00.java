package wang.sunnly.security.server00;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SecurityServerApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SecurityServerApplication00 {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServerApplication00.class, args);
    }
}
