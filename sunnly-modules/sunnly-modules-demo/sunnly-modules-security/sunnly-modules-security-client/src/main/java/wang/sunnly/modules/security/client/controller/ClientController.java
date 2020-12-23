package wang.sunnly.modules.security.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.security.user.feign.UserSecurityFeign;

import javax.annotation.Resource;

/**
 * ClientController
 *
 * @author Sunnly
 * @since 2020/12/23
 */
@RestController
public class ClientController {

    @Resource
    private UserSecurityFeign userSecurityFeign;
    @RequestMapping("aaa")
    public String aa(){

        return "ssss";
    }
}
