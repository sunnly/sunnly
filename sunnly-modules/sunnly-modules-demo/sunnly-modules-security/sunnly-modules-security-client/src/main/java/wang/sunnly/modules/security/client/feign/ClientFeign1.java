package wang.sunnly.modules.security.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.security.client.configuration.FeignRequestClientConfiguration;

/**
 * ClientFeign
 *
 * @author Sunnly
 * @since 2020/12/29
 */
@FeignClient(value = "macro-security-server")
public interface ClientFeign1 {

    @RequestMapping("server/aaa")
    ObjectResponse<String> aa();
}
