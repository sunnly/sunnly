package wang.sunnly.modules.logger.service.impl;

import org.springframework.stereotype.Component;
import wang.sunnly.modules.logger.service.LoggerService;

import java.util.Map;

/**
 * LoggerServiceImpl
 *
 * @author Sunnly
 * @since 2020/10/26 0026 21:52
 */
@Component
public class LoggerServiceImpl implements LoggerService {
    @Override
    public void save(String name, String method, String s, Map<String, String[]> parameterMap, String body, String value, String aaaa) {
        System.out.println(name);
    }
}
