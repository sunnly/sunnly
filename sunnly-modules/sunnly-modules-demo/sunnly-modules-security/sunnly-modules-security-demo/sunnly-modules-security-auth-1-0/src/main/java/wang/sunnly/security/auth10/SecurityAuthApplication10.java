package wang.sunnly.security.auth10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;
import wang.sunnly.security.annotation.EnableAuthClientSecurity;
import wang.sunnly.security.annotation.EnableClientSecurity;

/**
 * SecurityAuthApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthClientSecurity
@EnableFeignClients
public class SecurityAuthApplication10 {
    public static void main(String[] args) {
        SpringApplication.run(SecurityAuthApplication10.class, args);
    }
}
