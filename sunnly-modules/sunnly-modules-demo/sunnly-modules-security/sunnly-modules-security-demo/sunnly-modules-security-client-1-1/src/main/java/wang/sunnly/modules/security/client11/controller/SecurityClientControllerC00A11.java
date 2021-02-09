package wang.sunnly.modules.security.client11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.api.entity.JwtClientInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.modules.security.client11.feign.SecurityClientFeignC00A11;

/**
 * SecurityAuthController00
 *
 * @author Sunnly
 * @since 2021/1/7
 */
@RestController
@RequestMapping("C00A11")
public class SecurityClientControllerC00A11 {

    @Autowired(required = false)
    private SecurityClientFeignC00A11 securityClientFeignC00A11;

    @GetMapping("client")
    public JwtClientInfo selfClient() {
        System.out.println("=client=====SecurityAuthControllerS00:S00===================");
        return securityClientFeignC00A11.selfClient();
    }

    @GetMapping("user")
    public JwtUserInfo selfUser() {
        System.out.println("=user======SecurityAuthControllerS00:S00===================");
        return securityClientFeignC00A11.selfUser();
    }

    @GetMapping("jwt")
    public ListResponse<String> self() {
        System.out.println("=self======SecurityAuthController00:M00===================");
        return securityClientFeignC00A11.self();
    }
}
