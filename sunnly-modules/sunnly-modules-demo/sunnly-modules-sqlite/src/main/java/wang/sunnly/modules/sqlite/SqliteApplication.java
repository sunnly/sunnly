package wang.sunnly.modules.sqlite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SqliteApplication
 *
 * @author Sunnly
 * @since 2020/12/5 0005
 */
@SpringBootApplication
@MapperScan("wang.sunnly.modules.sqlite.mapper")
public class SqliteApplication {
    public static void main(String[] args) {
        SpringApplication.run(SqliteApplication.class,args);
    }
}
