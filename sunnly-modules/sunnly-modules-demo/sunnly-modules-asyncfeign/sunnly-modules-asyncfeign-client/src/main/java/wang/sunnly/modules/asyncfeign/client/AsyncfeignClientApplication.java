package wang.sunnly.modules.asyncfeign.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * AsyncfeignServerApplication
 *
 * @author Sunnly
 * @since 2020/10/30 0030 22:46
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AsyncfeignClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsyncfeignClientApplication.class, args);
    }
}
