package wang.sunnly.modules.nacos.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * NacosSupportApplication
 * NacosSupport启动测试类
 *
 * @author Sunnly
 * @since 2020/11/23 18:10
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"wang.sunnly.modules.nacos.support","wang.sunnly.nacos.support"})
public class NacosSupportApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosSupportApplication.class, args);
    }
}
