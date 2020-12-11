package wang.sunnly.modules.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.api.entity.UserInfo;

import java.util.Map;

/**
 * UserFeign
 *
 * @author Sunnly
 * @since 2020/12/8 0008
 */
@FeignClient("${macro.feign.client.admin}")
public interface UserFeign {

    @PostMapping("validate")
    public ObjectResponse<UserInfo> validate(@RequestBody Map<String, String> authInfo);
}
