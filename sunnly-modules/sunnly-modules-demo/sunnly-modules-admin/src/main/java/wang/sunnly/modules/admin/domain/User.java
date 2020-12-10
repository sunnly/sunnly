package wang.sunnly.modules.admin.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "base_user")
public class User {

    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    @Column(name = "`name`")
    private String name;

    /**
     * 性别(0:女,1:男)
     */
    @Column(name = "sex")
    private int sex;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * 电话
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 身份证
     */
    @Column(name = "idcard")
    private String idcard;

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
     * 用户类型(0:系统默认,1:后台用户,2:平台用户)
     */
    @Column(name = "`type`")
    private int type;

    /**
     * 用户渠道
     */
    @Column(name = "channel")
    private String channel;

    /**
     * 状态(0:删除,1:正常,2:锁定,11:待审核,12:审核失败)
     */
    @Column(name = "`status`")
    private String status;

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