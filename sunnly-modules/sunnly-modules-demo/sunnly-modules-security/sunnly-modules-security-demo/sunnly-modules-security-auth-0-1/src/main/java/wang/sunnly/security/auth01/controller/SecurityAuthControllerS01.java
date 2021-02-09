package wang.sunnly.security.auth01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.security.auth01.feign.SecurityAuthFeignS01;

/**
 * SecurityAuthController00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@RestController
@RequestMapping("S01")
public class SecurityAuthControllerS01 {

    @Autowired(required = false)
    private SecurityAuthFeignS01 securityAuthFeignS01;

    @GetMapping("client")
    public JwtClientInfo selfClient(){
        System.out.println("=client=====SecurityAuthControllerS01:S01===================");
        return securityAuthFeignS01.selfClient();
    }
    @GetMapping("user")
    public JwtUserInfo selfUser(){
        System.out.println("=user======SecurityAuthControllerS01:S01===================");
        return securityAuthFeignS01.selfUser();
    }

    @GetMapping("jwt")
    public ListResponse<String> self(){
        System.out.println("=self======SecurityAuthController00:M00===================");
        return securityAuthFeignS01.self();
    }
}
