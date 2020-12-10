package wang.sunnly.modules.admin.service;

import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.modules.admin.mapper.UserMapper;
import wang.sunnly.modules.api.entity.UserInfo;
import wang.sunnly.mysql.service.BaseService;

/**
 * UserService
 *
 * @author Sunnly
 * @since 2020/12/9 0009
 */
public interface UserService extends BaseService<UserMapper, User> {

    UserInfo validate(String username, String password);
}


