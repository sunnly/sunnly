package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.common.core.utils.SnowFlake;
import wang.sunnly.modules.admin.domain.GroupUser;
import wang.sunnly.modules.admin.mapper.GroupUserMapper;
import wang.sunnly.modules.admin.service.GroupService;
import wang.sunnly.modules.admin.service.GroupUserService;
import wang.sunnly.modules.admin.service.UserService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * GroupUserServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class GroupUserServiceImpl
        extends BaseServiceImpl<GroupUserMapper, GroupUser>
        implements GroupUserService, BaseService<GroupUserMapper, GroupUser> {

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private UserService userService;
    @Resource
    private GroupService groupService;

    @Override
    public int insertGroupUser(Long groupId, Long userId){
        // 验证部门和用户真实存在
        groupService.validateGroup(groupId);
        userService.validateUser(userId);
        //取消关联
        mapper.deleteGroupUser(userId);

        GroupUser groupUser = new GroupUser();
        groupUser.setGroupUserId(snowFlake.nextId());
        groupUser.setUserId(userId);
        groupUser.setGroupId(groupId);
        return mapper.insert(groupUser);
    }

    @Override
    public int insertGroupUsers(GroupUser groupUser){
        // 验证部门和用户真实存在
        groupService.validateGroup(groupUser.getGroupId());
        userService.validateUsers(groupUser.getUserIds());

        mapper.deleteGroupUsers(groupUser.getUserIds());
        List<GroupUser> groupUserList = new ArrayList<>(16);
        groupUser.getUserIds().forEach(userId -> {
            GroupUser gUser = new GroupUser();
            gUser.setGroupUserId(snowFlake.nextId());
            gUser.setUserId(userId);
            gUser.setGroupId(groupUser.getGroupId());
            groupUserList.add(gUser);
        });

        return mapper.insertGroupUsers(groupUserList);
    }

    @Override
    public int deleteGroupUsers(GroupUser groupUser){

        //TODO 待处理
        return mapper.deleteGroupUsers(groupUser.getUserIds());
    }
}
