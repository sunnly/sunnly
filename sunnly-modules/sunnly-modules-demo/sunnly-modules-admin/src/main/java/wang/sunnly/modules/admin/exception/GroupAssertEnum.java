package wang.sunnly.modules.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wang.sunnly.common.core.exception.assertion.MacroExceptionAssert;

/**
 * 组添加异常定义
 *
 * @author Sunnly
 * @since 2020/12/10 0027
 */
@AllArgsConstructor
@Getter
public enum GroupAssertEnum implements MacroExceptionAssert {

    PARENT_GROUP_NOT_EXIST(60100, "父分组不存在")
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
