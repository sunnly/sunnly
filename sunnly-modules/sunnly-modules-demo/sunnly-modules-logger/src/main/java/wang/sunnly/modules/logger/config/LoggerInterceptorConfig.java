package wang.sunnly.modules.logger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wang.sunnly.modules.logger.interceptor.LoggerInterceptor;
import wang.sunnly.modules.logger.service.LoggerService;

/**
 * LoggerInterceptorConfig
 *
 * @author Sunnly
 * @since 2020/10/26 0026 21:17
 */
@Configuration
public class LoggerInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    LoggerService loggerService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor(loggerService));
    }
}
