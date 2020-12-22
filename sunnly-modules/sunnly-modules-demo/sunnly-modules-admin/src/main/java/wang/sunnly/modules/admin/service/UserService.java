package wang.sunnly.modules.admin.service;

import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.modules.admin.mapper.UserMapper;
import wang.sunnly.common.api.entity.UserInfo;
import wang.sunnly.mysql.service.BaseService;

import javax.servlet.http.HttpServletRequest;

/**
 * UserService
 *
 * @author Sunnly
 * @since 2020/12/9 0009
 */
public interface UserService extends BaseService<UserMapper, User> {

    String getPassword(String username, String md5);

    UserInfo validate(HttpServletRequest request, String username, String password);
}


