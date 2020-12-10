package wang.sunnly.modules.admin.service;

import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.modules.admin.mapper.GroupMapper;
import wang.sunnly.mysql.service.BaseService;

public interface GroupService extends BaseService<GroupMapper, Group> {

    int insertGroup(Group entity, Long userId, String userName, String userIp);
}
