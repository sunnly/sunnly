package wang.sunnly.modules.admin.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.modules.admin.domain.Permission;
import wang.sunnly.modules.admin.mapper.PermissionMapper;
import wang.sunnly.modules.admin.service.PermissionService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PermissionServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class PermissionServiceImpl
        extends BaseServiceImpl<PermissionMapper, Permission>
        implements PermissionService, BaseService<PermissionMapper, Permission> {

    @Override
    public List<Map<String, String>> getColumn(JwtUserInfo userInfo, String permissionCode) {
        List<String> roles = null;

        String rolesStr = userInfo.getRoles();

        if (StringUtils.isNotEmpty(rolesStr)) {
            roles = ListUtil.toList(rolesStr.split(","));
        }

        return parseColumnRule(mapper.getPermission(userInfo.getUserId(),
                userInfo.getDept(), userInfo.getOrg(), roles, permissionCode, 1));
    }

    private List<Map<String, String>> parseColumnRule(List<Map<String, String>> column) {
        Map<String, String> map = new HashMap<>(16);
        for (int i = 0; i < column.size(); i++) {
            if ("y".equals(map.get(column.get(i).get("id")))) {
                column.remove(i);
                i--;
            } else {
                map.put(column.get(i).get("id"), "y");
            }
        }
        return column;
    }

    @Override
    public Map<String, String> getRowRule(JwtUserInfo userInfo, String permissionCode) {
        List<String> roles = null;
        userInfo.setUserId("10001");
        userInfo.setDept("100");
        userInfo.setOrg("200");
        userInfo.setRoles("300,301,302");
        String rolesStr = userInfo.getRoles();

        if (StringUtils.isNotEmpty(rolesStr)) {
            roles = ListUtil.toList(rolesStr.split(","));
        }

        //规则解析
        return parseRowRule(mapper.getPermission(userInfo.getUserId(),
                userInfo.getDept(), userInfo.getOrg(), roles, permissionCode, 2));
    }

    private Map<String, String> parseRowRule(List<Map<String, String>> rowRule) {
        rowRule = parseColumnRule(rowRule);
//  {
//      "rules": [{
//              "field": "OrderDate",
//              "op": "less",
//              "value": "2012-01-01"
//          },
//          {
//              "field": "CustomerID",
//              "op": "equal",
//              "value": "VINET"
//          }
//      ],
//      "op": "and",
//      "name": "aadfds"
//  }


        // TODO 自身数据权限...
        // where UserID = {CurrentUserID}
        // where UserID = {CurrentUserID}  or {CurrentUserID} in (领导)
        // where UserID = {CurrentUserID}  or ({CurrentUserID} in (销售经理)  and 销售金额 > 100000)
        Map<String, String> map = new HashMap<>();
        rowRule.stream().forEach(row ->{
            String rule = row.get("rule");
            if (StringUtils.isNotEmpty(rule)){
                Object parse = JSON.parse(rule);

            }
        });

        return map;
    }

}
