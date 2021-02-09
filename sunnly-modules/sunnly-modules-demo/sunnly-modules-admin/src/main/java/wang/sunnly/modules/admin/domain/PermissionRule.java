package wang.sunnly.modules.admin.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "base_permission_rule")
public class PermissionRule {
    /**
     * 权限列规则ID
     */
    @Column(name = "prule_id")
    private Long pruleId;

    /**
     * 权限ID
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 编码(用于注解匹配)
     */
    @Column(name = "permission_code")
    private String permissionCode;

    /**
     * 模块所属渠道
     */
    @Column(name = "client_channel")
    private String clientChannel;

    /**
     * 模式(0:禁用,1:允许)
     */
    @Column(name = "prule_permit")
    private Boolean prulePermit;

    /**
     * 权限类型(1:列权限,2:行权限)
     */
    @Column(name = "prule_type")
    private Boolean pruleType;

    /**
     * 规则，这里是字段名称
     */
    @Column(name = "prule_rule")
    private String pruleRule;

    /**
     * 状态(0:无效,1:有效)
     */
    @Column(name = "prule_status")
    private Boolean pruleStatus;

    /**
     * 该规则绑定角色用户数
     */
    @Column(name = "prule_count")
    private Integer pruleCount;
}
