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

    /**
     * 用户ID
     */
    private String id;
    /**
     * 登录名称
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机
     */
    private String phone;
    /**
     * 性别
     */
    private int sex;
    /**
     * 地址
     */
    private String address;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录时间
     */
    private String loginTime;
    /**
     * 所属机构，从顶级机构到所属机构
     */
    private List<String> orgNames;
    /**
     * 所属部门
     */
    private String deptName;
    /**
     * 角色列表
     */
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
