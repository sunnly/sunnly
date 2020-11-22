package wang.sunnly.modules.logger.service;

import java.util.Map;

/**
 * LoggerService
 *
 * @author Sunnly
 * @since 2020/10/26 0026 23:56
 */
public interface LoggerService {
    /**
     * 日志保存接口
     * @param name 日志名称
     * @param method 方法
     * @param s s
     * @param parameterMap 参数map
     * @param body body
     * @param value value
     * @param dest 描述
     */
    void save(String name, String method, String s, Map<String, String[]> parameterMap, String body, String value, String dest);
}
