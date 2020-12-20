package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.modules.admin.domain.Role;
import wang.sunnly.modules.admin.mapper.RoleMapper;
import wang.sunnly.modules.admin.service.RoleService;
import wang.sunnly.modules.api.entity.JwtUserInfo;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

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
        return list;
//        return mapper.getDictId(dictFiled);
    }
}
