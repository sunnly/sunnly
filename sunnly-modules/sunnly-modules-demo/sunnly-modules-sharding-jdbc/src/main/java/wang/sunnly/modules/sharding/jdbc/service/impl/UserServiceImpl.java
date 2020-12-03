package wang.sunnly.modules.sharding.jdbc.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.modules.sharding.jdbc.entity.User;
import wang.sunnly.modules.sharding.jdbc.mapper.UserMapper;
import wang.sunnly.modules.sharding.jdbc.service.UserService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

/**
 * UserServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/3
 */
@Service
public class UserServiceImpl
        extends BaseServiceImpl<UserMapper, User>
        implements UserService, BaseService<UserMapper,User> {
}
