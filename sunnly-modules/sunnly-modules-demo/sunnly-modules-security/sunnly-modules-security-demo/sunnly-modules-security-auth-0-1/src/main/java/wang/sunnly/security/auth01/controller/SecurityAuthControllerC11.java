package wang.sunnly.security.auth01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.security.auth01.feign.SecurityAuthFeignC11;

/**
 * SecurityAuthController00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@RestController
@RequestMapping("C11")
public class SecurityAuthControllerC11 {

    @Autowired(required = false)
    private SecurityAuthFeignC11 securityAuthFeignC11;

    @GetMapping("client")
    public JwtClientInfo selfClient(){
        System.out.println("=client=====SecurityAuthControllerC10:C10===================");
        return securityAuthFeignC11.selfClient();
    }
    @GetMapping("user")
    public JwtUserInfo selfUser(){
        System.out.println("=user======SecurityAuthControllerC10:C10===================");
        return securityAuthFeignC11.selfUser();
    }

    @GetMapping("jwt")
    public ListResponse<String> self(){
        System.out.println("=self======SecurityAuthController00:M00===================");
        return securityAuthFeignC11.self();
    }
}
