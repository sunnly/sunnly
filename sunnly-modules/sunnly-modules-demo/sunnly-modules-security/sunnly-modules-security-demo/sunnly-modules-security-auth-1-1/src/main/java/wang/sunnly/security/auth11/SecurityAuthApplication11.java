package wang.sunnly.security.auth11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;
import wang.sunnly.security.annotation.EnableAuthServerSecurity;

/**
 * SecurityAuthApplication
 *
 * @author Sunnly
 * @since 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthServerSecurity
@EnableFeignClients
@MapperScan("wang.sunnly.modules.auth.mapper")
public class SecurityAuthApplication11 {
    public static void main(String[] args) {
        SpringApplication.run(SecurityAuthApplication11.class, args);
    }
}
