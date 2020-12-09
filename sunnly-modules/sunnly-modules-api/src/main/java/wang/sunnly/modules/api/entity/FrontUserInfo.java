package wang.sunnly.modules.api.entity;

import lombok.Data;

import java.util.List;

/**
 * FrontUserInfo
 * 前端页面用户信息，封装了菜单权限信息
 *
 * @author Sunnly
 * @since 2020/12/8 0008
 */
@Data
public class FrontUserInfo {

    private String id;
    private String username;
    private String name;
    private String phone;
    private int sex;
    private String address;
    private String loginIp;
    private String loginTime;
    private List<String> orgNames;
    private String deptName;
    private List<String> roleNames;

    /**
     * 菜单权限
     */
    private List<PermissionInfo> menus;
    /**
     * 按钮权限
     */
    private List<PermissionInfo> elements;
}
