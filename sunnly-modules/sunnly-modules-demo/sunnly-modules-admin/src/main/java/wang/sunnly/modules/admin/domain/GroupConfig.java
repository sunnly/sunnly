package wang.sunnly.modules.admin.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "base_group_config")
public class GroupConfig {
    /**
     * 机构配置ID
     */
    @Id
    @Column(name = "gc_id")
    private Long gcId;

    /**
     * 机构ID
     */
    @Column(name = "group_id")
    private Long groupId;

    /**
     * 是否开启三元审核
     */
    @Column(name = "gc_three_manager")
    private String gcThreeManager;

    /**
     * 会话超时时间(0:不开启，单位分钟)
     */
    @Column(name = "gc_session")
    private Integer gcSession;

    /**
     * token有效时间(单位分钟)，此值必需设置，不设置默认为8小时
     */
    @Column(name = "gc_token")
    private Integer gcToken;

    /**
     * 允许在多久内刷新token(0:不允许刷新token，单位分钟，值需要大于gc_token的值才有效)
     */
    @Column(name = "gc_ref_token")
    private Integer gcRefToken;

    /**
     * 账户委托管理机构
     */
    @Column(name = "gc_agent_group_ids")
    private String gcAgentGroupIds;

    /**
     * 账户委托管理人员
     */
    @Column(name = "gc_agent_user_ids")
    private String gcAgentUserIds;
}