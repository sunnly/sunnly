package wang.sunnly.modules.encrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * EncryptApplication
 *
 * @author Sunnly
 * @since 2020/11/29 0029
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SercetGeneratorApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SercetGeneratorApplication.class,args);
    }
}
