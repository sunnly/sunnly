package wang.sunnly.modules.security.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.security.server.feign.ClientFeign2;
import wang.sunnly.security.user.feign.UserSecurityFeign;

import javax.annotation.Resource;

/**
 * ServerController
 *
 * @author Sunnly
 * @since 2020/12/29
 */
@RestController
public class ServerController {

    @RequestMapping("server/aaa")
    public ObjectResponse<String> aa(){
        System.out.println("=================================");
        return new ObjectResponse<String>().setData("ssss");
    }
}
