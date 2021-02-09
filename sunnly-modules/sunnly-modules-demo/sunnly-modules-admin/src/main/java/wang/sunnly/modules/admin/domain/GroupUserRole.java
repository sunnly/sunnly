package wang.sunnly.modules.admin.domain;

import lombok.Data;
import wang.sunnly.security.domain.MacroDomain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "base_group_user_role")
public class GroupUserRole implements MacroDomain {
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

    private transient List<Long> roles;

    @Override
    public void setUpdateUserId(Long updateUserId) {

    }

    @Override
    public void setUpdateUserName(String updateUserName) {

    }

    @Override
    public void setUpdateUserIp(String updateUserIp) {

    }

    @Override
    public void setUpdateTime(String updateTime) {

    }

    @Override
    public Long getUpdateUserId() {
        return null;
    }

    @Override
    public String getUpdateUserName() {
        return null;
    }

    @Override
    public String getUpdateUserIp() {
        return null;
    }

    @Override
    public String getUpdateTime() {
        return null;
    }
}
