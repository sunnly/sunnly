package wang.sunnly.modules.auth.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.api.entity.AuthenticationRequest;
import wang.sunnly.modules.auth.domain.SystemConfig;
import wang.sunnly.modules.auth.exception.AuthAssertEnum;
import wang.sunnly.modules.auth.service.AuthService;
import wang.sunnly.modules.auth.service.SystemConfigService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserController
 * 用户
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@RestController
@RequestMapping("/jwt")
public class AuthController {

    @Resource
    private AuthService authService;

    @Resource
    private SystemConfigService systemConfigService;

    @GetMapping("code/{username}")
    public String getCode(@PathVariable("username") String username){

        String random = RandomStringUtils.randomNumeric(4);
        authService.setValidateCode(username, random);
        return random;
//        try {
//            ServletOutputStream out = response.getOutputStream();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    @PostMapping("/token")
    public ObjectResponse<String> createTokenByAccount(@RequestBody AuthenticationRequest authReq) {

        //断言账户锁定状态
        AuthAssertEnum.ACCOUT_LOCK.assertGt(0,
                new Double(Math.ceil(
                        authService.lockedTime(authReq.getUsername()) / 60.0))
                        .longValue());
//        获取账号登录方式
        String channel =
                StringUtils.isEmpty(authReq.getScSystemChannel())
                        ? "system" : authReq.getScSystemChannel();
        SystemConfig sysConfigByChannel = systemConfigService.getSysConfigByChannel(channel);
        AuthAssertEnum.CHANNEL_ERROR.assertNotNull(sysConfigByChannel);
        //账号登录已开启
        AuthAssertEnum.LOGIN_ACCOUNT_NOT_ALLOWED.assertEquals(sysConfigByChannel.getScAccount(), "1");
        //验证码判断
        if (StringUtils.equals(sysConfigByChannel.getScAccountValidate(), "1")) {
            AuthAssertEnum.INCORRECT_VERIFICATION.assertNotNull(authReq.getCode());
            AuthAssertEnum.INCORRECT_VERIFICATION.assertEquals(
                    authReq.getCode(),
                    authService.getValidateCode(authReq.getUsername()));
            authService.removeValidateCode(authReq.getUsername());
        }

        //账户验证
        return new ObjectResponse<String>().setData(authService.login(authReq.getUsername(), authReq.getPassword()));
    }

    @PostMapping("ukey/token")
    public ObjectResponse<String> createTokenByUkey(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ObjectResponse<String>().setData("success");
    }
}
