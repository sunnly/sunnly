package wang.sunnly.modules.logger.interceptor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import wang.sunnly.modules.logger.annotation.IgnoreMacroLogger;
import wang.sunnly.modules.logger.annotation.MacroLogger;
import wang.sunnly.modules.logger.service.LoggerService;
import wang.sunnly.modules.logger.wrapper.ParamsRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * LoggerInterceptor
 *
 * @author Sunnly
 * @since 2020/10/26 0026 21:17
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private final LoggerService loggerService;

    public LoggerInterceptor(LoggerService loggerService){
        this.loggerService = loggerService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String body = null;
        String name = null;
        String value = null;
        String desc = null;
        if (request instanceof ParamsRequestWrapper) {
            ParamsRequestWrapper req = (ParamsRequestWrapper) request;
            body = req.getBody();
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequestMapping methodAnnotation = handlerMethod.getMethodAnnotation(RequestMapping.class);
            System.out.println(methodAnnotation.name());

            MacroLogger methodAnnotation1 = handlerMethod.getMethodAnnotation(MacroLogger.class);
            if (methodAnnotation1 != null) {

                name = methodAnnotation1.name();
                desc = methodAnnotation1.desc();
                value = methodAnnotation1.value();
            }else{
                if (handlerMethod.getMethodAnnotation(IgnoreMacroLogger.class)==null){
                    System.out.println("请记录日志");
                    throw new RuntimeException("请记录日志。。。。。");
                }
            }
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);
        loggerService.save(name, request.getMethod(), value,parameterMap, body, value, desc);
        return super.preHandle(request, response, handler);
    }
}
