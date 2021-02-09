package wang.sunnly.modules.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import wang.sunnly.common.web.msg.result.ListResponse;

import java.util.Map;

/**
 * UserFeign
 * 获取用户权限信息
 *
 * @author Sunnly
 * @since 2021/1/22
 */
@FeignClient("${macro.feign.client.admin:macro-admin}")
public interface UserFeign {

    @GetMapping("/user/permission")
    ListResponse<Map<String,String>> getUserPermission();

}
