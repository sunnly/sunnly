package wang.sunnly.modules.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wang.sunnly.common.core.exception.assertion.MacroExceptionAssert;

/**
 * AuthAssertEnum
 * 鉴权异常
 *
 * @author Sunnly
 * @since 2020/12/11
 */
@AllArgsConstructor
@Getter
public enum AuthAssertEnum implements MacroExceptionAssert {

    /**
     * 账号已被锁定
     */
    CHANNEL_ERROR(60100, "渠道有误，请查证后重新访问"),
    /**
     * 账号已被锁定
     */
    ACCOUT_LOCK(60100, "该账号已被锁定，请{2}分钟后重试..."),
    /**
     * 登录异常
     */
    LOGIN_ERROR(60101, "登录异常"),
    /**
     * 账号登录已禁用
     */
    LOGIN_ACCOUNT_NOT_ALLOWED(60102, "账号登录已禁用"),
    /**
     * 验证码输入有误
     */
    INCORRECT_VERIFICATION(60103, "验证码输入有误"),
    /**
     * 用户名或密码错误
     */
    USERNAME_PASSWORD_NOT_MATCH(60001, "用户名或密码错误"),
    ;

    /**
     * 返回码
     */
    private final int code;
    /**
     * 返回消息
     */
    private final String message;
}
