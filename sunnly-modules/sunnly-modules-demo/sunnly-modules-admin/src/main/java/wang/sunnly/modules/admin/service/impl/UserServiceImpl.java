package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.modules.admin.entity.User;
import wang.sunnly.modules.admin.mapper.UserMapper;
import wang.sunnly.modules.admin.service.UserService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

/**
 * UserServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/9 0009
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User>
        implements UserService, BaseService<UserMapper,User> {
}
