package wang.sunnly.modules.admin.domain;

import lombok.Data;
import wang.sunnly.security.domain.MacroDomain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "base_role")
public class Role implements MacroDomain {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 角色编码
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 父角色ID
     */
    @Column(name = "role_parent_id")
    private Long roleParentId;

    /**
     * 父角色
     */
    @Column(name = "role_parent_name")
    private String roleParentName;

    /**
     * 角色列表
     */
    @Column(name = "role_ids")
    private String roleIds;

    /**
     * 角色类型(0:系统角色,1:三元角色,2:普通角色)
     */
    @Column(name = "role_type")
    private int roleType;

    /**
     * 角色描述
     */
    @Column(name = "role_desc")
    private String roleDesc;

    /**
     * 状态(0:删除,1:正常,11:待审核,12:审核不通过)
     */
    @Column(name = "role_status")
    private int roleStatus;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 创建人ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 创建人名称
     */
    @Column(name = "create_user_name")
    private String createUserName;

    /**
     * 创建人IP
     */
    @Column(name = "create_user_ip")
    private String createUserIp;

    /**
     * 修改日期
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 修改人ID
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 修改人名称
     */
    @Column(name = "update_user_name")
    private String updateUserName;

    /**
     * 修改人IP
     */
    @Column(name = "update_user_ip")
    private String updateUserIp;
}
