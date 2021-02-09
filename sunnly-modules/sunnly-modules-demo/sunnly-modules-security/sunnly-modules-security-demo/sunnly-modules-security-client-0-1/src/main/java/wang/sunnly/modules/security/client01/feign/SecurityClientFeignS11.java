package wang.sunnly.modules.security.client01.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import com.alibaba.fastjson.JSONObject;
import wang.sunnly.common.web.msg.result.ListResponse;

/**
 * SecurityClientFeign00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@FeignClient("macro-server-1-1")
public interface SecurityClientFeignS11 {

    @GetMapping("/M11/client")
    JwtClientInfo selfClient();

    @GetMapping("/M11/user")
    JwtUserInfo selfUser();

    @GetMapping("/M11/jwt")
    ListResponse<String> self();
}
