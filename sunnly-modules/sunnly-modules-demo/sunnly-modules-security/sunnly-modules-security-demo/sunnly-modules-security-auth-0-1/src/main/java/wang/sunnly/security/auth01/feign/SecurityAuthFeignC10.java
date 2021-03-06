package wang.sunnly.security.auth01.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.common.web.msg.result.ListResponse;

/**
 * SecurityAuthFeign00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@FeignClient("macro-client-1-0")
public interface SecurityAuthFeignC10 {

    @GetMapping("/M10/client")
    JwtClientInfo selfClient();

    @GetMapping("/M10/user")
    JwtUserInfo selfUser();

    @GetMapping("/M10/jwt")
    ListResponse<String> self();
}
