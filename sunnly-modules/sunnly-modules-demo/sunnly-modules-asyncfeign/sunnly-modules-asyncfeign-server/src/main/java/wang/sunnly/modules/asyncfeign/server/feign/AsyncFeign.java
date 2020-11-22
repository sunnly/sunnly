package wang.sunnly.modules.asyncfeign.server.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wang.sunnly.common.web.msg.result.ObjectResult;
import wang.sunnly.modules.asyncfeign.server.feign.impl.AsyncFeignImpl;

import java.util.Map;

/**
 * AsyncFeign
 *
 * @author Sunnly
 * @since 2020/10/30 0030 23:40
 */
@FeignClient(value = "sunnly-modules-async-client", configuration = AsyncFeignImpl.class)
public interface AsyncFeign {
    /**
     * 发送
     * @param send send
     * @return ObjectResult
     */
    @RequestMapping("send")
    ObjectResult<String> send(@RequestParam("send") String send);
}
