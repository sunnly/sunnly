package wang.sunnly.modules.asyncfeign.server.feign.impl;

import org.springframework.cloud.openfeign.FeignClient;
import wang.sunnly.common.web.msg.result.ObjectResult;
import wang.sunnly.modules.asyncfeign.server.feign.AsyncFeign;

import java.util.Map;

/**
 * AsyncFeign
 *
 * @author Sunnly
 * @since 2020/10/30 0030 23:40
 */
public class AsyncFeignImpl implements AsyncFeign {
    @Override
    public ObjectResult<String> send(String key) {
        return null;
    }
}
