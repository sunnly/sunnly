package wang.sunnly.modules.sharding.jdbc.service;

import wang.sunnly.modules.sharding.jdbc.entity.User;
import wang.sunnly.modules.sharding.jdbc.mapper.UserMapper;
import wang.sunnly.mysql.service.BaseService;

/**
 * UserService
 * 用户Service
 *
 * @author Sunnly
 * @since 2020/12/3
 */
public interface UserService extends BaseService<UserMapper, User> {

    int insertUser(User user);
}
