package wang.sunnly.modules.admin.domain;

import javax.persistence.*;
import lombok.Data;

/**
    * 字段权限表
    */
@Data
@Table(name = "base_permission")
public class Permission {
    /**
     * ID
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 模块编码
     */
    @Column(name = "client_code")
    private String clientCode;

    /**
     * 模块名称
     */
    @Column(name = "client_name")
    private String clientName;

    /**
     * 模块所属渠道
     */
    @Column(name = "client_channel")
    private String clientChannel;

    /**
     * 编码(用于注解匹配)
     */
    @Column(name = "permission_code")
    private String permissionCode;

    /**
     * 名称
     */
    @Column(name = "permission_name")
    private String permissionName;

    /**
     * 是否有效
     */
    @Column(name = "permission_status")
    private Boolean permissionStatus;

    /**
     * 描述
     */
    @Column(name = "permission_dest")
    private String permissionDest;

    /**
     * 值域(Json格式)
     */
    @Column(name = "permission_fields")
    private String permissionFields;
}