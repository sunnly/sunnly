package wang.sunnly.modules.security.client.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * MyFeignClient
 *
 * @author Sunnly
 * @since 2020/12/23
 */
@FeignClient("aaa")
public interface MyFeignClient {
}
