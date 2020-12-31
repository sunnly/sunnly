package wang.sunnly.modules.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;
import wang.sunnly.security.annotation.EnableAuthServerSecurity;
import wang.sunnly.validate.annotation.EnableMacroEmail;

/**
 * AuthApplication
 * 鉴权服务启动类
 *
 * @author Sunnly
 * @since 2020/12/8
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("wang.sunnly.modules.auth.mapper")
@EnableMacroEmail
@EnableAuthServerSecurity
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
