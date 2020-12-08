package wang.sunnly.modules.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * UserFeign
 *
 * @author Sunnly
 * @since 2020/12/8 0008
 */
@FeignClient("${macro.feign.client.admin}")
public interface UserFeign {
}
