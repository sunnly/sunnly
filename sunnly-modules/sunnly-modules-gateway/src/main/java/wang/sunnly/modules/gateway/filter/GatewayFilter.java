package wang.sunnly.modules.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * GatewayFilter
 *
 * @author Sunnly
 * @since 2019/8/2 12:02
 */
public class GatewayFilter implements GlobalFilter {

    private static final String GATEWAY_PREFIX = "/api";

    /**
     * 网关过滤器
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LinkedHashSet requiredAttribute = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String requsetUri = request.getPath().pathWithinApplication().value();

        if (requiredAttribute != null) {
            Iterator<URI> iterator = requiredAttribute.iterator();
            while (iterator.hasNext()) {
                URI next = iterator.next();
                if (next.getPath().startsWith(GATEWAY_PREFIX)) {
                    requsetUri = next.getPath().substring(GATEWAY_PREFIX.length());
                }
            }
        }

        String method = request.getMethod().toString();
        ServerHttpRequest.Builder mutate = request.mutate();
        //放行不拦截的请求
        if (accessUri(requsetUri)) {
            return chain.filter(exchange.mutate().request(mutate.build()).build());
        }

        //权限过滤
        //获取用户信息

        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private boolean accessUri(String uri) {
        //TODO 配置文件中获取白名单请求
        return false;
    }
}
