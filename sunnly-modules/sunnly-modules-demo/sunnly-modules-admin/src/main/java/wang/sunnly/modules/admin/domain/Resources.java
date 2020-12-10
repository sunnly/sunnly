package wang.sunnly.modules.admin.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "base_resources")
public class Resources {
    /**
     * 资源ID
     */
    @Id
    @Column(name = "res_id")
    private Long resId;

    /**
     * 资源编码
     */
    @Column(name = "res_code")
    private String resCode;

    /**
     * 资源名称
     */
    @Column(name = "res_name")
    private String resName;

    /**
     * 资源标题
     */
    @Column(name = "res_title")
    private String resTitle;

    /**
     * 资源图标
     */
    @Column(name = "res_icon")
    private String resIcon;

    /**
     * 资源类型(1:页面,2:按钮,3:API)
     */
    @Column(name = "res_type")
    private String resType;

    /**
     * 资源服务器路径
     */
    @Column(name = "res_path")
    private String resPath;

    /**
     * 资源请求类型
     */
    @Column(name = "res_method")
    private String resMethod;

    /**
     * 资源页面路径
     */
    @Column(name = "res_view")
    private String resView;

    /**
     * 父资源ID
     */
    @Column(name = "res_parent_id")
    private Long resParentId;

    /**
     * 资源列表
     */
    @Column(name = "res_ids")
    private String resIds;

    /**
     * 关联资源(拥有此资源权限的用户同时有用该关联资源的所有权限)
     */
    @Column(name = "res_binds")
    private String resBinds;

    /**
     * 资源组(一般针对组合按钮)
     */
    @Column(name = "res_group")
    private String resGroup;

    /**
     * 资源描述
     */
    @Column(name = "res_desc")
    private String resDesc;

    /**
     * 资源排序
     */
    @Column(name = "res_order")
    private Integer resOrder;

    /**
     * 资源状态(0:禁用,1:启用)
     */
    @Column(name = "res_status")
    private String resStatus;

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