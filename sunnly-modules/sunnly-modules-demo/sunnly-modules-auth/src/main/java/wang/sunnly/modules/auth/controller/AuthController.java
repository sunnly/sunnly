package wang.sunnly.modules.auth.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import wang.sunnly.common.api.entity.AuthenticationRequest;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.auth.domain.SystemConfig;
import wang.sunnly.modules.auth.exception.AuthAssertEnum;
import wang.sunnly.modules.auth.service.AuthService;
import wang.sunnly.modules.auth.service.SystemConfigService;
import wang.sunnly.validate.service.ValidateService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    private static final String VALIDATE = "1";

    @Resource
    private AuthService authService;

    @Resource
    private ValidateService validateService;

    @Resource
    private SystemConfigService systemConfigService;

    @PostMapping("/token")
    public ObjectResponse<String> createTokenByAccount(@RequestBody AuthenticationRequest authRequest,
                                                       HttpServletRequest request, HttpServletResponse response) {

        //断言账户锁定状态
        AuthAssertEnum.ACCOUT_LOCK.assertGreater(0,
                new Double(Math.ceil(authService.lockedTime(authRequest.getUsername()) / 60.0)).longValue());
        //获取账号登录方式
        String channel = StringUtils.isEmpty(authRequest.getChannel()) ? "system" : authRequest.getChannel();
        SystemConfig sysConfigByChannel = systemConfigService.getSysConfigByChannel(channel);
        AuthAssertEnum.CHANNEL_ERROR.assertNotNull(sysConfigByChannel);
        //账号登录已开启
        AuthAssertEnum.LOGIN_ACCOUNT_NOT_ALLOWED.assertEquals(sysConfigByChannel.getScAccount(), "1");
        //验证码判断
        if (StringUtils.equals(sysConfigByChannel.getScAccountValidate(), VALIDATE)) {
            AuthAssertEnum.INCORRECT_VERIFICATION.assertNotNull(authRequest.getCode());
            AuthAssertEnum.INCORRECT_VERIFICATION.assertIsTrue(
                    validateService.validateCode(new ServletWebRequest(request, response),
                    "image", authRequest.getCodeId(), authRequest.getCode()));
        }

        //账户验证
        return new ObjectResponse<String>().setData(authService.login(authRequest.getUsername(), authRequest.getPassword()));
    }

    @PostMapping("ukey/token")
    public ObjectResponse<String> createTokenByUkey(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ObjectResponse<String>().setData("success");
    }
}
