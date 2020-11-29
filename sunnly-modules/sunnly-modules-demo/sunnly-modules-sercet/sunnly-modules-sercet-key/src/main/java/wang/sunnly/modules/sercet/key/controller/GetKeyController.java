package wang.sunnly.modules.sercet.key.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ListResponse;

/**
 * GetKeyController
 *
 * @author Sunnly
 * @since 2020/11/29 0029
 */
@RestController
public class GetKeyController {

    @Value("${macro.sunnly.test1}")
    private String test1;

    @Value("${macro.sunnly.test2}")
    private String test2;

    @GetMapping("test")
    public ListResponse<String> get() {
        return new ListResponse<String>().add(test1).add(test2);
    }
}
