package wang.sunnly.security.auth11.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.security.server.api.service.MacroTokenDomainService;

import java.util.ArrayList;
import java.util.List;

/**
 * SecurityAuthController11
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@RestController
@RequestMapping("M11")
public class SecurityAuthController11 {

    @Autowired(required = false)
    private MacroTokenDomainService macroTokenDomainService;

    @GetMapping("client")
    public JwtClientInfo selfClient(){
        System.out.println("=client=====SecurityAuthController11:M11===================");
        if (macroTokenDomainService!=null){
            JwtClientInfo clientInfo = macroTokenDomainService.getClientInfo();
            System.out.println(clientInfo);
            return clientInfo;
        }
        return null;
    }
    @GetMapping("user")
    public JwtUserInfo selfUser(){
        System.out.println("=user======SecurityAuthController11:M11===================");
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
        System.out.println("=self======SecurityAuthController11:M11===================");
        if (macroTokenDomainService!=null){

            JwtClientInfo clientInfo = macroTokenDomainService.getClientInfo();
            System.out.println(clientInfo);
            list.add(JSONObject.toJSONString(clientInfo));

            JwtUserInfo userInfo = macroTokenDomainService.getUserInfo();
            System.out.println(userInfo);
            list.add(JSONObject.toJSONString(userInfo));

        }
        return new ListResponse<String>().addData(list);
    }

}
