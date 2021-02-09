package wang.sunnly.security.server10.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import com.alibaba.fastjson.JSONObject;
import wang.sunnly.common.web.msg.result.ListResponse;

/**
 * SecurityServerFeign00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@FeignClient("macro-server-0-1")
public interface SecurityServerFeignS01 {

    @GetMapping("/M01/client")
    JwtClientInfo selfClient();

    @GetMapping("/M01/user")
    JwtUserInfo selfUser();

    @GetMapping("/M01/jwt")
    ListResponse<String> self();
}