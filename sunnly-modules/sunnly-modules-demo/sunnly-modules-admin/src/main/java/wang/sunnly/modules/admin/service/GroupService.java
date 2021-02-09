package wang.sunnly.modules.admin.service;

import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.modules.admin.mapper.GroupMapper;
import wang.sunnly.mysql.service.BaseService;

import java.util.List;
/**
 * GroupService
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface GroupService extends BaseService<GroupMapper, Group> {

    /**
     * 插入
     *
     * @param entity   group对象
     * @return 影响条目
     */
    int insertGroup(Group entity);

    List<Group> getChildren(long parentId, int exclude);

    int getGroupCount(Long groupId);

    int getGroupsCount(List<Long> groupIds);

    void validateGroup(Long groupId);

    void validateGroups(List<Long> groupIds);

    int deleteGroup(Group group, boolean cascading);

    int updateGroup(Group entity);

    int deleteGroups(Group group);
}
