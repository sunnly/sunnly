package wang.sunnly.modules.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wang.sunnly.common.core.exception.assertion.MacroExceptionAssert;

/**
 * 用户异常定义
 *
 * @author Sunnly
 * @since 2020/12/10 0027
 */
@AllArgsConstructor
@Getter
public enum UserAssertEnum implements MacroExceptionAssert {

    /**
     * 用户名不能为空
     */
    USERNAME_NOT_NULL(60000, "用户名不能为空"),
    /**
     * 用户名或密码错误
     */
    USERNAME_PASSWORD_NOT_MATCH(60001, "用户名或密码错误"),
    /**
     * token不能为空
     */
    USER_TOKEN_NOT_EMPTY(60002, "token不能为空")
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
