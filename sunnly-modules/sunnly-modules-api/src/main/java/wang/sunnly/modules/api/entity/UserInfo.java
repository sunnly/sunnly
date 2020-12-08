package wang.sunnly.modules.api.entity;

import lombok.Data;

/**
 * UserInfo
 * 用户信息，登录验证成功后返回的信息
 *
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@Data
public class UserInfo {

    private String userId;
    private String username;
    private String name;
    private int sex;
    private String birthday;
    private String mobile;
    private String email;
    private String idCard;
    private String countryCode;
    private String countryName;
    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;
    private String areaCode;
    private String areaName;
    private String streetCode;
    private String streetName;
    private String addr;
    private int type;
    private String channel;

    private String roles;
    private String dept;
    private String org;

}
