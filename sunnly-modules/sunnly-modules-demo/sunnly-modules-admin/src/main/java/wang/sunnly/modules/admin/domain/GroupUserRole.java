package wang.sunnly.modules.admin.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "base_group_user_role")
public class GroupUserRole {
    /**
     * 组/用户与角色关联ID
     */
    @Id
    @Column(name = "group_user_role_id")
    private Long groupUserRoleId;

    /**
     * 组ID
     */
    @Column(name = "group_id")
    private Long groupId;

    /**
     * 组列表
     */
    @Column(name = "group_ids")
    private String groupIds;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

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
}