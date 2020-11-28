package wang.sunnly.modules.scheduled.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wang.sunnly.common.web.exception.assertion.BusinessExceptionAssert;

import javax.servlet.http.HttpServletResponse;

/**
 * SelfResponseEnum
 *
 * @author Sunnly
 * @since 2020/11/29
 */
@Getter
@AllArgsConstructor
public enum SelfResponseEnum implements BusinessExceptionAssert {
    /**
     * 400
     */
    MYSELFRESPONSE(40400, "自定义enum异常");

    /**
     * 返回码
     */
    private final int code;
    /**
     * 返回消息
     */
    private final String message;
}
