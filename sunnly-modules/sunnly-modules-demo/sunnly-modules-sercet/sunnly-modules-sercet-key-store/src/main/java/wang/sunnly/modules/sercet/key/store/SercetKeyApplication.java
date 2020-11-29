package wang.sunnly.modules.sercet.key.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * SercetKeyApplication
 *
 * @author Sunnly
 * @since 2020/11/29 0029
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SercetKeyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SercetKeyApplication.class,args);
    }
}
