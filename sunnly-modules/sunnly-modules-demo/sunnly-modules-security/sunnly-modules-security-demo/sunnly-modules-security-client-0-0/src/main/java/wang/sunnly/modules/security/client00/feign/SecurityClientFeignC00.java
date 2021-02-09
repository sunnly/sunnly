package wang.sunnly.modules.security.client00.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import com.alibaba.fastjson.JSONObject;
import wang.sunnly.common.web.msg.result.ListResponse;

import java.util.List;

/**
 * SecurityClientFeign00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@FeignClient("macro-client-0-0")
public interface SecurityClientFeignC00 {

    @GetMapping("/M00/client")
    JwtClientInfo selfClient();

    @GetMapping("/M00/user")
    JwtUserInfo selfUser();

    @GetMapping("/M00/jwt")
    ListResponse<String> self();
}
