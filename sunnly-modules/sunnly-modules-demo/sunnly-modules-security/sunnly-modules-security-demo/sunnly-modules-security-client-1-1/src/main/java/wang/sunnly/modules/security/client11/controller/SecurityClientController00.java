package wang.sunnly.modules.security.client11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import com.alibaba.fastjson.JSONObject;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.security.ignore.annotation.IgnoreClientToken;
import wang.sunnly.security.ignore.annotation.IgnoreUserToken;
import wang.sunnly.security.server.api.service.MacroTokenDomainService;

import java.util.ArrayList;
import java.util.List;

/**
 * SecurityClientController00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@RestController
@RequestMapping("M11")
public class SecurityClientController00 {

    @Autowired(required = false)
    private MacroTokenDomainService macroTokenDomainService;

    @GetMapping("client")
    public JwtClientInfo selfClient(){
        System.out.println("=client=====SecurityClientController00:M11===================");
        if (macroTokenDomainService!=null){
            JwtClientInfo clientInfo = macroTokenDomainService.getClientInfo();
            System.out.println(clientInfo);
            return clientInfo;
        }
        return null;
    }
    @GetMapping("user")
    public JwtUserInfo selfUser(){
        System.out.println("=user======SecurityClientController00:M11===================");
        if (macroTokenDomainService!=null){
            JwtUserInfo userInfo = macroTokenDomainService.getUserInfo();
            System.out.println(userInfo);
            return userInfo;
        }
        return null;
    }

    @GetMapping("jwt")
    public ListResponse<String> self(){
        List<String> list = new ArrayList();
        System.out.println("=self======SecurityClientController00:M11===================");
        if (macroTokenDomainService!=null){

            JwtClientInfo clientInfo = macroTokenDomainService.getClientInfo();
            System.out.println(clientInfo);
            list.add(JSONObject.toJSONString(clientInfo));

            JwtUserInfo userInfo = macroTokenDomainService.getUserInfo();
            System.out.println(userInfo);
            list.add(JSONObject.toJSONString(userInfo));

        }
        return new ListResponse<>(list);
    }


    @GetMapping("client1")
    @IgnoreUserToken
    public JwtClientInfo selfClient1(){
        return selfClient();
    }
    @GetMapping("user1")
    @IgnoreUserToken
    public JwtUserInfo selfUser1(){
        return selfUser();
    }

    @GetMapping("jwt1")
    @IgnoreUserToken
    public ListResponse<String> self1(){
        return self();
    }


    @GetMapping("client2")
    @IgnoreClientToken
    public JwtClientInfo selfClient2(){
        return selfClient();
    }
    @GetMapping("user2")
    @IgnoreClientToken
    public JwtUserInfo selfUser2(){
        return selfUser();
    }

    @GetMapping("jwt2")
    @IgnoreClientToken
    public ListResponse<String> self2(){
        return self();
    }



    @GetMapping("client3")
    @IgnoreClientToken
    @IgnoreUserToken
    public JwtClientInfo selfClient3(){
        return selfClient();
    }
    @GetMapping("user3")
    @IgnoreClientToken
    @IgnoreUserToken
    public JwtUserInfo selfUser3(){
        return selfUser();
    }

    @GetMapping("jwt3")
    @IgnoreClientToken
    @IgnoreUserToken
    public ListResponse<String> self3(){
        return self3();
    }

}
