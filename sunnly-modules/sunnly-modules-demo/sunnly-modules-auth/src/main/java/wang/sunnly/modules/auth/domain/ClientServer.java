package wang.sunnly.modules.auth.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "auth_client_server")
public class ClientServer {
    /**
     * ID
     */
    @Column(name = "cs_id")
    private Long csId;

    /**
     * 服务ID
     */
    @Column(name = "service_id")
    private Long serviceId;

    /**
     * 允许访问的客户端ID
     */
    @Column(name = "client_id")
    private Long clientId;

    /**
     * 描述
     */
    @Column(name = "cs_description")
    private String csDescription;

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