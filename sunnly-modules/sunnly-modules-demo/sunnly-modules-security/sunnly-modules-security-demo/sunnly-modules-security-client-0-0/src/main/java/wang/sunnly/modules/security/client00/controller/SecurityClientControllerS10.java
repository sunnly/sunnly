package wang.sunnly.modules.security.client00.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import com.alibaba.fastjson.JSONObject;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.modules.security.client00.feign.SecurityClientFeignS10;

import java.util.List;

/**
 * SecurityAuthController00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@RestController
@RequestMapping("S10")
public class SecurityClientControllerS10 {

    @Autowired(required = false)
    private SecurityClientFeignS10 SecurityClientFeignS10;

    @GetMapping("client")
    public JwtClientInfo selfClient(){
        System.out.println("=client=====SecurityAuthControllerS10:S10===================");
        return SecurityClientFeignS10.selfClient();
    }
    @GetMapping("user")
    public JwtUserInfo selfUser(){
        System.out.println("=user======SecurityAuthControllerS10:S10===================");
        return SecurityClientFeignS10.selfUser();
    }

    @GetMapping("jwt")
    public ListResponse<String> self(){
        System.out.println("=self======SecurityAuthController00:M00===================");
        return SecurityClientFeignS10.self();
    }
}
