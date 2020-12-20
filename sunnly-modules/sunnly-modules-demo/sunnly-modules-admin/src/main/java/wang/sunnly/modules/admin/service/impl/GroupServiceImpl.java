package wang.sunnly.modules.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import org.springframework.stereotype.Service;
import wang.sunnly.common.core.utils.SnowFlake;
import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.modules.admin.exception.GroupAssertEnum;
import wang.sunnly.modules.admin.mapper.GroupMapper;
import wang.sunnly.modules.admin.service.GroupService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl
        extends BaseServiceImpl<GroupMapper, Group>
        implements GroupService, BaseService<GroupMapper, Group> {

    @Resource
    private SnowFlake snowFlake;

    @Override
    public int insertGroup(Group entity, Long userId, String userName, String userIp) {

        Long groupId = snowFlake.nextId();
        entity.setGroupId(groupId);
        Long groupParentId = entity.getGroupParentId();
        if (groupParentId != null && groupParentId != 0) {
            Group parentGroup = mapper.selectByPrimaryKey(groupParentId);
            //断言不为空
            GroupAssertEnum.PARENT_GROUP_NOT_EXIST.assertNotNull(parentGroup);
            entity.setGroupParentName(parentGroup.getGroupName());
            //设置组列表 父组列表+自己的列表
            entity.setGroupIds(
                    (parentGroup.getGroupIds() == null ? "" : parentGroup.getGroupIds())
                            .concat(",").concat(groupId + ""));
            //TODO 暂时设置允许所有父访问，后期根据参数配置
            entity.setGroupAuthIds(
                    (parentGroup.getGroupIds() == null ? "" : parentGroup.getGroupIds())
                            .concat(",").concat(groupId + ""));
        } else {
            entity.setGroupIds(groupId + "");
            entity.setGroupAuthIds(null);
            entity.setGroupParentId(0L);
        }
        //状态为正常
        entity.setGroupStatus("1");
        entity.setType(1);
        entity.setCreateTime(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        entity.setCreateUserId(userId);
        entity.setCreateUserName(userName);
        entity.setCreateUserIp(userIp);

        return mapper.insert(entity);
    }

    @Override
    public List<Group> getChildren(long parentId, int exclude) {
        return mapper.getChildren(parentId, exclude);
    }
    @Override
    public List<Map<String,Object>> query(long parentId, int exclude) {
        return mapper.query(parentId, exclude);
    }
}
