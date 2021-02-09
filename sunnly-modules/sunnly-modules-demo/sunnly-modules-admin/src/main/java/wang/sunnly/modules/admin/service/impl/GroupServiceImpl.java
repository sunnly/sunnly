package wang.sunnly.modules.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import wang.sunnly.common.api.exception.UserAssertEnum;
import wang.sunnly.common.core.utils.MacroStringUtils;
import wang.sunnly.common.core.utils.SnowFlake;
import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.modules.admin.mapper.GroupMapper;
import wang.sunnly.modules.admin.service.GroupService;
import wang.sunnly.modules.admin.service.UserService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;
import wang.sunnly.security.server.api.service.MacroTokenDomainService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GroupServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class GroupServiceImpl
        extends BaseServiceImpl<GroupMapper, Group>
        implements GroupService, BaseService<GroupMapper, Group> {

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private UserService userService;

    @Resource
    private MacroTokenDomainService macroTokenDomainService;

    @Override
    public int insertGroup(Group entity) {

        Long groupId = snowFlake.nextId();
        entity.setGroupId(groupId);
        Long groupParentId = entity.getGroupParentId();
        if (groupParentId != null && groupParentId != 0) {
            Group parentGroup = mapper.selectByPrimaryKey(groupParentId);
            //断言不为空
            UserAssertEnum.PARENT_GROUP_NOT_EXIST.assertNotNull(parentGroup);
            entity.setGroupParentName(parentGroup.getGroupName());

            //设置组列表 父组列表+自己的列表
            entity.setGroupIds(
                    (parentGroup.getGroupIds() == null ? ""
                            : parentGroup.getGroupIds().concat(",")
                    ).concat(groupId + ""));

            int controllable = entity.getControllable();
            if (StringUtils.isEmpty(entity.getGroupAuthIds())) {
                String authIds = MacroStringUtils.cutLast(parentGroup.getGroupIds(), ",", controllable);
                entity.setGroupAuthIds(
                        (parentGroup.getGroupIds() == null ? ""
                                : StringUtils.isEmpty(authIds) ? "" : authIds.concat(",")
                        ).concat(groupId + ""));
            }

        } else {
            entity.setGroupIds(groupId + "");
            if (StringUtils.isEmpty(entity.getGroupAuthIds())) {
                entity.setGroupAuthIds(groupId + "");
            }
            entity.setGroupParentId(0L);
            entity.setGroupParentName(null);
        }
        //状态为正常
        entity.setGroupStatus("1");
        entity.setType(1);
        return mapper.insert(entity);
    }

    @Override
    public List<Group> getChildren(long parentId, int exclude) {
        return mapper.getChildren(parentId, exclude);
    }

    @Override
    public int getGroupCount(Long groupId) {
        return mapper.getGroupCount(groupId);
    }

    @Override
    public int getGroupsCount(List<Long> groupIds) {
        return mapper.getGroupsCount(groupIds);
    }

    @Override
    public void validateGroup(Long groupId) {
        UserAssertEnum.USER_TOKEN_CONFIG_EMPTY.assertEquals(getGroupCount(groupId), 1);
    }

    @Override
    public void validateGroups(List<Long> groupIds) {
        UserAssertEnum.USER_TOKEN_CONFIG_EMPTY.assertEquals(getGroupsCount(groupIds), groupIds.size());
    }

    @Override
    public int deleteGroup(Group group, boolean cascading) {

        List<Group> children = mapper.getChildren(group.getGroupId(), 0);
        if (cascading) {
            // 级联删除 子机构、部门
            List<Long> groupIds = new ArrayList<>(16);

            children.forEach(group1 -> {
                macroTokenDomainService.copyUpdateInfo(group, group1);
                deleteGroup(group1, true);
                groupIds.add(group1.getGroupId());
            });
            // 级联删除 人员

            // 将当前机构、部门也添加进去查询用户信息
            groupIds.add(group.getGroupId());
            List<Map<String, Long>> userByGroupIds = mapper.getUserGroupByGroupIds(groupIds);
            if (userByGroupIds.size() > 0) {
                // 删除部门人员关联表
                mapper.deleteUserGroupByGroupIds(groupIds);
                // 删除人员
                List<Long> userIds = new ArrayList<>(16);
                userByGroupIds.stream().forEach(g -> {
                    userIds.add(g.get("userId"));
                });
                User users = new User();
                macroTokenDomainService.copyUpdateInfo(group, users);
                users.setUserIds(userIds);
                userService.deleteUsers(users);
            }


        } else {

            // 非级联删除
            if (children != null && children.size() > 0) {
                UserAssertEnum.GROUP_USED.assertFail();
            }

            //查询当前机构部门下是否有人员
            List<Long> groupIds = new ArrayList<>(16);
            groupIds.add(group.getGroupId());
            List<Map<String, Long>> userGroupByGroupIds = mapper.getUserGroupByGroupIds(groupIds);
            if (userGroupByGroupIds != null && userGroupByGroupIds.size() > 0) {
                UserAssertEnum.GROUP_USED.assertFail();
            }
        }
        return mapper.deleteGroup(group);
    }

    @Override
    public int deleteGroups(Group group) {
        List<Long> groupIds = group.getGroups();
        UserAssertEnum.GROUP_ID_NOT_EXIST.assertEquals(mapper.getGroupsCount(groupIds), groupIds.size());
        return mapper.deleteGroups(group);
    }

    @Override
    public int updateGroup(Group entity) {
        Group group = mapper.selectByPrimaryKey(entity.getGroupId());
        UserAssertEnum.GROUP_ID_NOT_EXIST.assertNotNull(group);

        //设置不允许修改的值
        macroTokenDomainService.copyCreateInfo(group, entity);
        entity.setGroupName(group.getGroupName());
        entity.setGroupIds(group.getGroupIds());
        entity.setGroupAuthIds(group.getGroupAuthIds());
        if (StringUtils.isEmpty(entity.getGroupStatus())) {
            entity.setGroupStatus(group.getGroupStatus());
        }
        if (entity.getParentId() == null) {
            entity.setGroupParentId(group.getParentId());
        } else if (!StringUtils.equals(entity.getParentId() + "", group.getParentId() + "")) {
            Group parentGroup = mapper.selectByPrimaryKey(entity.getParentId());
            //断言不为空
            UserAssertEnum.PARENT_GROUP_NOT_EXIST.assertNotNull(parentGroup);
            entity.setGroupParentName(parentGroup.getGroupName());
        }


        return mapper.updateByPrimaryKey(entity);
    }

}
