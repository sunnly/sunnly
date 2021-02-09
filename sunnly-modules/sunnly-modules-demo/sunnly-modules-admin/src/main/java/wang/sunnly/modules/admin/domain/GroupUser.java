package wang.sunnly.modules.admin.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "base_group_user")
public class GroupUser {
    /**
     * 组用户关联ID
     */
    @Id
    @Column(name = "group_user_id")
    private Long groupUserId;

    /**
     * 组ID
     */
    @Column(name = "group_id")
    private Long groupId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    private transient List<Long> userIds;
}
