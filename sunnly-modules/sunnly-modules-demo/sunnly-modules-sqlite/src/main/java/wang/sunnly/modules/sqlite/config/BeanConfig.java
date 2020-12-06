package wang.sunnly.modules.sqlite.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * BeanConfig
 *
 * @author Sunnly
 * @since 2020/12/6 0006
 */
@Configuration
public class BeanConfig {

    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:sqlite::resource:test.db");
        hikariConfig.setDriverClassName("org.sqlite.JDBC");
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }
//    @Bean
//    public DataSource dataSource(){
//        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
//        sqLiteDataSource.setUrl("jdbc:sqlite:F:/work/ideaspace/macro/sunnly/sunnly-modules/sunnly-modules-demo/sunnly-modules-sqlite/src/main/resources/test.db");
//        SQLiteConfig sqLiteConfig = new SQLiteConfig();
//        return sqLiteDataSource;
//    }
//
//    @Bean
//    public Test test() {
//        return new Test();
//    }
}
