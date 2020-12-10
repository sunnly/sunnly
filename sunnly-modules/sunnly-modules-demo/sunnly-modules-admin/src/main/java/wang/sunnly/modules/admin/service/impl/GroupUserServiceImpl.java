package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import wang.sunnly.modules.admin.domain.GroupUser;
import wang.sunnly.modules.admin.mapper.GroupUserMapper;
import wang.sunnly.modules.admin.mapper.GroupUserMapper;
import wang.sunnly.modules.admin.service.GroupUserService;
import wang.sunnly.modules.admin.service.GroupUserService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

@Service
public class GroupUserServiceImpl
        extends BaseServiceImpl<GroupUserMapper, GroupUser>
        implements GroupUserService, BaseService<GroupUserMapper, GroupUser> {


}
