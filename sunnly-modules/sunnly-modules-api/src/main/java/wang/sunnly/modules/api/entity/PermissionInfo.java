package wang.sunnly.modules.api.entity;

import lombok.Data;

/**
 * PermissionInfo
 * 权限
 *
 * @author Sunnly
 * @since 2020/12/9
 */
@Data
public class PermissionInfo {

    private String id;
    private String code;
    private String name;
    private String title;
    private String icon;
    private String path;
    private String method;
    private String view;
    private String parentId;
    private String ids;
    private String binds;
    private String group;
    private String desc;
    private String order;
    private String status;
}
