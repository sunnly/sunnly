package wang.sunnly.modules.gateway.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.common.web.handler.BaseThreadLocalHandler;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.modules.gateway.feign.UserFeign;
import wang.sunnly.modules.gateway.properties.MacroGateIgnoreProperties;
import wang.sunnly.security.server.api.service.MacroTokenDomainService;
import wang.sunnly.security.user.properties.UserSecurityProperties;

import javax.annotation.Resource;
import java.net.URI;
import java.util.*;

/**
 * GatewayFilter
 *
 * @author Sunnly
 * @since 2019/8/2 12:02
 */
@Configuration
public class GatewayFilter implements GlobalFilter {

    private static final String GATEWAY_PREFIX = "/api";

    @Resource
    private UserSecurityProperties userSecurityProperties;

    @Resource
    private MacroTokenDomainService macroTokenDomainService;

    @Resource
    private MacroGateIgnoreProperties macroGateIgnoreProperties;

    @Resource
    private UserFeign userFeign;

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
        System.out.println(request.getHeaders().get("postman-token"));
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
        //获取用户信息 TODO
//        JwtUserInfo userInfo = getUserInfo(request);
//
//        ListResponse<Map<String, String>> userPermission = userFeign.getUserPermission();
//
//        System.out.println(userPermission);

        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    public JwtUserInfo getUserInfo(ServerHttpRequest request) {
        List<String> strings = request.getHeaders().get(userSecurityProperties.getTokenHeader());
        String token = null;
        if (strings != null) {
            token = strings.get(0);
        }
        if (StringUtils.isBlank(token)) {
            strings = request.getQueryParams().get("token");
            if (strings != null) {
                token = strings.get(0);
            }
        }
        BaseThreadLocalHandler.setToken(token);
        return macroTokenDomainService.getUserInfo(token);
    }

    private boolean accessUri(String uri) {
        return macroGateIgnoreProperties.getIgnores().stream()
                .filter(s -> uri.startsWith(s)).count() > 0;
    }
}
