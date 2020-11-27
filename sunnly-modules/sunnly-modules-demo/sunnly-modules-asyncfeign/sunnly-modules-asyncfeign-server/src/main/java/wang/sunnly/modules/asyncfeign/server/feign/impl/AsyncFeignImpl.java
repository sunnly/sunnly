package wang.sunnly.modules.asyncfeign.server.feign.impl;

import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.asyncfeign.server.feign.AsyncFeign;

/**
 * AsyncFeign
 *
 * @author Sunnly
 * @since 2020/10/30 0030 23:40
 */
public class AsyncFeignImpl implements AsyncFeign {
    @Override
    public ObjectResponse<String> send(String key) {
        return null;
    }
}
