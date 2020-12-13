package wang.sunnly.modules.auth.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "auth_client")
public class Client {
    /**
     * 主键
     */
    @Column(name = "client_id")
    private Long clientId;

    /**
     * 服务ID
     */
    @Column(name = "client_code")
    private String clientCode;

    /**
     * 秘钥
     */
    @Column(name = "client_secret")
    private String clientSecret;

    /**
     * 服务名称
     */
    @Column(name = "client_name")
    private String clientName;

    /**
     * 服务状态(0:停止使用,1:正常,2:暂停使用)
     */
    @Column(name = "client_status")
    private Boolean clientStatus;

    /**
     * 所属渠道
     */
    @Column(name = "client_channel")
    private String clientChannel;

    /**
     * 系统级别(1:系统内置,2:系统级别,3:外部申请)
     */
    @Column(name = "client_system")
    private Boolean clientSystem;

    /**
     * 描述
     */
    @Column(name = "client_description")
    private String clientDescription;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 创建用户ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 创建用户
     */
    @Column(name = "create_user_name")
    private String createUserName;

    /**
     * 创建用户IP
     */
    @Column(name = "create_user_ip")
    private String createUserIp;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 修改用户ID
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 修改用户
     */
    @Column(name = "update_user_name")
    private String updateUserName;

    /**
     * 修改用户IP
     */
    @Column(name = "update_user_ip")
    private String updateUserIp;
}