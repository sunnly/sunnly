package wang.sunnly.modules.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;
import wang.sunnly.mysql.annotation.EnableMacroDatabase;
import wang.sunnly.security.annotation.EnableMacroUserSecurity;

/**
 * AdminApplication
 * 资源服务入口
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@MapperScan("wang.sunnly.modules.admin.mapper")
@EnableMacroDatabase
@EnableMacroUserSecurity
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
