package wang.sunnly.modules.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import wang.sunnly.mysql.feign.MacroPermissionFeign;

/**
 * PermissionFeign
 *
 * @author Sunnly
 * @since 2020/12/22 0022
 */
@FeignClient("${macro.feign.client.admin}")
public interface PermissionFeign extends MacroPermissionFeign {
}
