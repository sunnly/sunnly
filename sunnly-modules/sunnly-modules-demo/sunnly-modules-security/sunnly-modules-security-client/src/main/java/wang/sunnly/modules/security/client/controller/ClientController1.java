package wang.sunnly.modules.security.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.security.client.feign.ClientFeign1;
import wang.sunnly.modules.security.client.service.ClientService1;
import wang.sunnly.security.ignore.annotation.IgnoreClientToken;

import javax.annotation.Resource;

/**
 * ClientController
 *
 * @author Sunnly
 * @since 2020/12/23
 */
@RestController
@IgnoreClientToken
public class ClientController1 {

    @Resource
    private ClientFeign1 clientFeign;


    @Resource
    private ClientService1 clientService1;

    @RequestMapping("aaa")
//    @IgnoreUserToken
    public ObjectResponse<String> aa(){
        System.out.println("-------------------");
//        String ddd = clientService1.aaaaa("ddd");
//        System.out.println(ddd);
        return clientFeign.aa();
//        return new ObjectResponse<>();
    }
}
