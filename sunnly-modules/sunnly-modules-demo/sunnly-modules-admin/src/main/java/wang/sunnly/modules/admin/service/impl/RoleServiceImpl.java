package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.modules.admin.domain.Role;
import wang.sunnly.modules.admin.mapper.RoleMapper;
import wang.sunnly.modules.admin.service.RoleService;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;
/**
 * RoleServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class RoleServiceImpl
        extends BaseServiceImpl<RoleMapper, Role>
        implements RoleService, BaseService<RoleMapper, Role> {

    @Override
    public List<String> getDictId(String dictFiled, JwtUserInfo userInfo) {
        List<String> list = new ArrayList<>();
        if (userInfo.getUsername().equals("admin")){
            return list;
        }
        list.add("addr");
        list.add("group_id");
        list.add("group_phone");
        list.add("create_time");
        list.add("group_parent_id");
        list.add("fullname");
        list.add("group_fullname");
        return list;
//        return mapper.getDictId(dictFiled);
    }
}
