package wang.sunnly.modules.admin.domain.rule;

/**
 * Type
 *
 * @author Sunnly
 * @since 2020/12/23
 */
public enum Type {

    /**
     * 组，一般标志是()
     */
    GROUP_TYPE,
    /**
     * 字符串类型
     */
    STRING_TYPE,
    /**
     * 日期类型
     */
    DATE_TYPE,
    /**
     * 对应FILED中所在数据库中的值，
     */
    COLUMN_TYPE
    ;
}
