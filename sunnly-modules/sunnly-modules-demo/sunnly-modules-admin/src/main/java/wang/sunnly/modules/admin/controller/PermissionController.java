package wang.sunnly.modules.admin.controller;

import org.springframework.web.bind.annotation.*;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.domain.Permission;
import wang.sunnly.modules.admin.service.PermissionService;
import wang.sunnly.mysql.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    public ListResponse<List<String>> getColumn(@RequestBody JwtUserInfo userInfo,
                                                             @PathVariable("permissionCode") String permissionCode) {
        return new ListResponse<>(service.getColumn(userInfo, permissionCode));
    }

    @PostMapping("column1/{permissionCode}")
    public ListResponse<List<String>> getColumn1(@PathVariable("permissionCode") String permissionCode,
                                                 HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        return new ListResponse<>(service.getColumn(null, permissionCode));
    }

    @PostMapping("row/{permissionCode}")
    public ObjectResponse<Map<String, String>> getRowRule(@RequestBody JwtUserInfo userInfo,
                                                          @PathVariable("permissionCode") String permissionCode) {
        return new ObjectResponse<>(service.getRowRule(userInfo, permissionCode));
    }
}
