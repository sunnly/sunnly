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
     *
     */
    ACCOUT_LOCK(60100, "该账号已被锁定，请稍后再试..."),
    /**
     *
     */
    LOGIN_ERROR(60101, "登录异常")
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
