package wang.sunnly.modules.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.modules.auth.feign.PermissionFeign;

import javax.annotation.Resource;

/**
 * TestController
 *
 * @author Sunnly
 * @since 2020/12/23
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Resource
    private PermissionFeign permissionFeign;

    @GetMapping("")
    public ListResponse<String> list(){
        permissionFeign.getColumn1("aaaddd");
        return new ListResponse<String>().addData("sdfs");
    }

}
