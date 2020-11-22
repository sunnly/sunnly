package wang.sunnly.modules.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * LoggerApplication
 *
 * @author Sunnly
 * @since 2020/10/26 0026 20:54
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LoggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class, args);
    }
}
