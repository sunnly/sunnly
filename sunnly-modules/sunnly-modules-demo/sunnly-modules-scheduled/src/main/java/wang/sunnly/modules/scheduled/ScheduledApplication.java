package wang.sunnly.modules.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import wang.sunnly.scheduled.annotation.EnableMacroSchedule;

/**
 * ScheduledApplication
 *
 * @author Sunnly
 * @since 2020/10/22 0022 20:47
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableMacroSchedule
public class ScheduledApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScheduledApplication.class, args);
    }
}
