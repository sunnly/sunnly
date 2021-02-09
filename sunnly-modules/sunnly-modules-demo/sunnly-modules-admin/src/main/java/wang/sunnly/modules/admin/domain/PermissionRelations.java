package wang.sunnly.modules.admin.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "base_permission_relations")
public class PermissionRelations {
    /**
     * 数据权限角色关联表
     */
    @Column(name = "pr_id")
    private Long prId;

    /**
     * 类型(1:用户,2:角色,3:组(机构、部门))
     */
    @Column(name = "pr_external_type")
    private Byte prExternalType;

    /**
     * 用户、角色、机构、部门ID
     */
    @Column(name = "pr_external_id")
    private Long prExternalId;

    /**
     * 行权限ID、列权限ID
     */
    @Column(name = "prule_id")
    private Long pruleId;

    /**
     * 排序(排序值越大优先级越高，等排序值下，用户、角色、机构内部排序)
     */
    @Column(name = "pr_order")
    private Integer prOrder;
}
