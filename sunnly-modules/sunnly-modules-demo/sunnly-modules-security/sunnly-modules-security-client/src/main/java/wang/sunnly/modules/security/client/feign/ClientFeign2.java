package wang.sunnly.modules.security.client.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * ClientFeign2
 *
 * @author Sunnly
 * @since 2020/12/31
 */
@FeignClient("macro-auth")
public interface ClientFeign2 {
}
