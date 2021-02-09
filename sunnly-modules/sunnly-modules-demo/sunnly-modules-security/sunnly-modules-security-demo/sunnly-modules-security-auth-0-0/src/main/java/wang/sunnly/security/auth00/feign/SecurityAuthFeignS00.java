package wang.sunnly.security.auth00.feign;

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
@FeignClient("macro-server-0-0")
public interface SecurityAuthFeignS00 {

    @GetMapping("/M00/client")
    JwtClientInfo selfClient();

    @GetMapping("/M00/user")
    JwtUserInfo selfUser();

    @GetMapping("/M00/jwt")
    ListResponse<String> self();
}
