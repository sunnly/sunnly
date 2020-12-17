package wang.sunnly.modules.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserInfo
 * 用户信息，登录验证成功后返回的信息
 *
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;
    /**
     * 用户姓名
     */
    private String name;

    /**
     * 性别(0:女,1:男)
     */
    private int sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 国家编码
     */
    private String countryCode;

    /**
     * 国家
     */
    private String countryName;

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市
     */
    private String cityName;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 区域
     */
    private String areaName;

    /**
     * 街道编码
     */
    private String streetCode;

    /**
     * 街道
     */
    private String streetName;

    /**
     * 详细地址
     */
    private String addr;

    /**
     * 用户登录IP地址
     */
    private String loginIp;

    /**
     * 登录时间
     */
    private String loginTime;

    /**
     * 用户类型(0:系统默认,1:后台用户,2:平台用户)
     */
    private int type;

    /**
     * 用户渠道
     */
    private String channel;

    private String roles;
    private String dept;
    private String org;

}
