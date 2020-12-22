package wang.sunnly.modules.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.sunnly.modules.admin.interceptor.ColumnPremissionInterceptor;

/**
 * MysqlConfig
 *
 * @author Sunnly
 * @since 2020/12/19 0019
 */
@Configuration
public class MyBatisConfig {

//    @Bean
//    DataAuthorInterceptor dataAuthorInterceptor() {
//        DataAuthorInterceptor interceptor = new DataAuthorInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty(PageInterceptor.PROPERTIES_KEY_DATABASE_TYPE, PageInterceptor.DATABASE_TYPE_MYSQL);
//        properties.setProperty(PageInterceptor.PROPERTIES_KEY_PAGE_EXPRESSION_MATCHING, ".*Page.*");
//        properties.setProperty(PageInterceptor.PROPERTIES_KEY_COUNT_SUFFIX, "_COUNT");
//        interceptor.setProperties(properties);
//        return interceptor;
//    }

//    @Bean
//    ColumnPremissionInterceptor columnPremissionInterceptor(){
//        return new ColumnPremissionInterceptor();
//    }

}
