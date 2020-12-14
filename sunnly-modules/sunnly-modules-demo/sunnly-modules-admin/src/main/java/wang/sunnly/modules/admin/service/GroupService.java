package wang.sunnly.modules.admin.service;

import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.modules.admin.mapper.GroupMapper;
import wang.sunnly.mysql.service.BaseService;

public interface GroupService extends BaseService<GroupMapper, Group> {

    /**
     * 插入
     * @param entity group对象
     * @param userId 用户ID
     * @param userName 用户名称
     * @param userIp 用户IP
     * @return 影响条目
     */
    int insertGroup(Group entity, Long userId, String userName, String userIp);
}
