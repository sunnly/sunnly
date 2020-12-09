package wang.sunnly.modules.admin.service;

import wang.sunnly.modules.admin.entity.User;
import wang.sunnly.modules.admin.mapper.UserMapper;
import wang.sunnly.mysql.service.BaseService;

/**
 * UserService
 *
 * @author Sunnly
 * @since 2020/12/9 0009
 */
public interface UserService extends BaseService<UserMapper, User> {
}
