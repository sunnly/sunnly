package wang.sunnly.modules.admin.service.impl;

import cn.hutool.core.collection.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.modules.admin.domain.DataRules;
import wang.sunnly.modules.admin.domain.Permission;
import wang.sunnly.modules.admin.domain.rule.Rules;
import wang.sunnly.modules.admin.mapper.PermissionMapper;
import wang.sunnly.modules.admin.service.PermissionService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<List<String>> getColumn(JwtUserInfo userInfo, String permissionCode) {
        List<String> roles = null;
        String rolesStr = userInfo.getRoles();

        if (StringUtils.isNotEmpty(rolesStr)) {
            roles = ListUtil.toList(rolesStr.split(","));
        }

        return parseColumnRule(mapper.getPermission(userInfo.getUserId(),
                userInfo.getDept(), userInfo.getOrg(), roles, permissionCode, 1));
    }

    @Override
    public Map<String, String> getRowRule(JwtUserInfo userInfo, String permissionCode) {
        List<String> roles = null;
        String rolesStr = userInfo.getRoles();
        if (StringUtils.isNotEmpty(rolesStr)) {
            roles = ListUtil.toList(rolesStr.split(","));
        }
        //规则解析
        return parseRowRule(mapper.getPermission(userInfo.getUserId(),
                userInfo.getDept(), userInfo.getOrg(), roles, permissionCode, 2));
    }

    private List<List<String>> parseColumnRule(List<DataRules> column) {
        Map<String, List<DataRules>> collect = column.stream().collect(
                Collectors.groupingBy(DataRules::getRule));

        List<String> accessList = new ArrayList<>();
        List<String> forbidList = new ArrayList<>();

        collect.keySet().stream().forEach(c -> {
            DataRules dataRules = collect.get(c).get(0);
            if (dataRules.isPermit()){
                accessList.add(c);
            }else {
                forbidList.add(c);
            }
        });
        List<List<String>> res = new ArrayList<>(2);
        res.add(accessList);
        res.add(forbidList);
        return res;
    }

    private Map<String, String> parseRowRule(List<DataRules> rowRule) {
        Map<String, String> map = new HashMap<>(16);
        List<List<String>> rowRules = parseColumnRule(rowRule);
        // 这里只有access的
        List<String> accessList = rowRules.get(0);
        // 通过list转换为字符串
        accessList.stream().forEach(rule -> {
            Map<String, Rules> rulesMap = Rules.getRulesMap(rule);
            for (String next: rulesMap.keySet()){
                map.put(next, rulesMap.get(next).toConditions());
            }
        });
        return map;
    }

}
