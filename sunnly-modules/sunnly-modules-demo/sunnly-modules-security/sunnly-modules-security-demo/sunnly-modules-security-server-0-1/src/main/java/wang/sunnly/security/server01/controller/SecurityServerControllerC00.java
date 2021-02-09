package wang.sunnly.security.server01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import com.alibaba.fastjson.JSONObject;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.security.server01.feign.SecurityServerFeignC00;

/**
 * SecurityAuthController00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@RestController
@RequestMapping("C00")
public class SecurityServerControllerC00 {

    @Autowired(required = false)
    private SecurityServerFeignC00 SecurityServerFeignC00;

    @GetMapping("client")
    public JwtClientInfo selfClient(){
        System.out.println("=client=====SecurityAuthControllerC00:C00===================");
        return SecurityServerFeignC00.selfClient();
    }
    @GetMapping("user")
    public JwtUserInfo selfUser(){
        System.out.println("=user======SecurityAuthControllerC00:C00===================");
        return SecurityServerFeignC00.selfUser();
    }

    @GetMapping("jwt")
    public ListResponse<String> self(){
        System.out.println("=self======SecurityAuthController00:M00===================");
        return SecurityServerFeignC00.self();
    }

}
