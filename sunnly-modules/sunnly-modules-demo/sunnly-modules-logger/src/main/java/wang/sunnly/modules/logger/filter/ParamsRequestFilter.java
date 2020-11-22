package wang.sunnly.modules.logger.filter;

import org.springframework.context.annotation.Configuration;
import wang.sunnly.modules.logger.wrapper.ParamsRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ParamsRequestFilter
 *
 * @author Sunnly
 * @since 2020/10/26 0026 21:08
 */
@Configuration
public class ParamsRequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new ParamsRequestWrapper((HttpServletRequest)request), response);
    }
}
