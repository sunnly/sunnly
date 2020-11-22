package wang.sunnly.modules.asyncfeign.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import wang.sunnly.asyncfeign.annotation.EnableMacroAsyncFeign;

/**
 * AsyncfeignServerApplication
 *
 * @author Sunnly
 * @since 2020/10/30 0030 22:46
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableMacroAsyncFeign
@EnableFeignClients
public class AsyncfeignServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsyncfeignServerApplication.class, args);
    }
}
