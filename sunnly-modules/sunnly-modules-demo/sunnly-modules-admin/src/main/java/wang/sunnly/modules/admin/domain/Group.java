package wang.sunnly.modules.admin.domain;

import lombok.Data;
import wang.sunnly.common.core.utils.tree.BaseTreeNode;
import wang.sunnly.security.domain.MacroDomain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "base_group")
public class Group extends BaseTreeNode<Long> implements MacroDomain {
    /**
     * 组ID
     */
    @Id
    @Column(name = "group_id")
    private Long groupId;

    /**
     * 组编码
     */
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 组名称
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 组全称
     */
    @Column(name = "group_fullname")
    private String groupFullname;

    /**
     * 父组ID
     */
    @Column(name = "group_parent_id")
    private Long groupParentId;

    /**
     * 父组名称
     */
    @Column(name = "group_parent_name")
    private String groupParentName;

    /**
     * 组列表(顶级组,一级组,二级组,本组)
     */
    @Column(name = "group_ids")
    private String groupIds;

    /**
     * 授权组(允许哪些组访问)
     */
    @Column(name = "group_auth_ids")
    private String groupAuthIds;

    /**
     * 负责人Id
     */
    @Column(name = "group_manager_id")
    private Long groupManagerId;

    /**
     * 负责人
     */
    @Column(name = "group_manager_name")
    private String groupManagerName;

    /**
     * 座机电话
     */
    @Column(name = "group_phone")
    private String groupPhone;

    /**
     * 联系电话
     */
    @Column(name = "group_mobile")
    private String groupMobile;

    /**
     * 描述
     */
    @Column(name = "group_desc")
    private String groupDesc;

    /**
     * 组类型(1:机构,2:部门)
     */
    @Column(name = "group_method")
    private Byte groupMethod;

    /**
     * 状态(0:删除,1:正常,11:待审核,12:审核不通过)
     */
    @Column(name = "group_status")
    private String groupStatus;

    /**
     * 国家编码
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * 国家
     */
    @Column(name = "country_name")
    private String countryName;

    /**
     * 省编码
     */
    @Column(name = "province_code")
    private String provinceCode;

    /**
     * 省
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 城市编码
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 城市
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 区域编码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 区域
     */
    @Column(name = "area_name")
    private String areaName;

    /**
     * 街道编码
     */
    @Column(name = "street_code")
    private String streetCode;

    /**
     * 街道
     */
    @Column(name = "street_name")
    private String streetName;

    /**
     * 详细地址
     */
    @Column(name = "addr")
    private String addr;

    /**
     * 组类型(0:系统组,1:普通组)
     */
    @Column(name = "`type`")
    private int type;
    /**
     * 排序
     */
    @Column(name = "group_order")
    private int groupOrder;

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


    @Override
    public Long getId() {
        return this.groupId;
    }

    @Override
    public Long getParentId() {
        return this.groupParentId == null ? 0L : this.groupParentId;
    }

    @Override
    public Integer getSort() {
        return this.getGroupOrder();
    }

    @Override
    public String getName() {
        return this.getGroupName();
    }

}
