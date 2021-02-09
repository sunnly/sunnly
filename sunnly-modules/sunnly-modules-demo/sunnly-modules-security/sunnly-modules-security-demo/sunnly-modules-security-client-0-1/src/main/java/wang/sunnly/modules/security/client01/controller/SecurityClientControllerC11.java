package wang.sunnly.modules.security.client01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import com.alibaba.fastjson.JSONObject;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.modules.security.client01.feign.SecurityClientFeignC11;

/**
 * SecurityAuthController00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@RestController
@RequestMapping("C11")
public class SecurityClientControllerC11 {

    @Autowired(required = false)
    private SecurityClientFeignC11 SecurityClientFeignC11;

    @GetMapping("client")
    public JwtClientInfo selfClient(){
        System.out.println("=client=====SecurityAuthControllerC10:C10===================");
        return SecurityClientFeignC11.selfClient();
    }
    @GetMapping("user")
    public JwtUserInfo selfUser(){
        System.out.println("=user======SecurityAuthControllerC10:C10===================");
        return SecurityClientFeignC11.selfUser();
    }

    @GetMapping("jwt")
    public ListResponse<String> self(){
        System.out.println("=self======SecurityAuthController00:M00===================");
        return SecurityClientFeignC11.self();
    }
}
