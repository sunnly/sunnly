package wang.sunnly.modules.admin.controller;

import org.springframework.web.bind.annotation.*;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.domain.Permission;
import wang.sunnly.modules.admin.service.PermissionService;
import wang.sunnly.mysql.controller.BaseController;

import java.util.Map;

/**
 * PermissionController
 *
 * @author Sunnly
 * @since 2020/12/22 0022
 */
@RestController
@RequestMapping("permission")
public class PermissionController extends BaseController<PermissionService, Permission> {

    @PostMapping("column/{permissionCode}")
    public ListResponse<Map<String, String>> getColumn(@RequestBody JwtUserInfo userInfo,
                                                       @PathVariable("permissionCode") String permissionCode) {
        return new ListResponse<>(service.getColumn(userInfo, permissionCode));
    }

    @PostMapping("row/{permissionCode}")
    public ObjectResponse<Map<String, String>> getRowRule(JwtUserInfo userInfo, String permissionCode) {
        return new ObjectResponse<>(service.getRowRule(userInfo, permissionCode));
    }
}
