package wang.sunnly.modules.admin.service;

import wang.sunnly.common.api.entity.UserInfo;
import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.modules.admin.mapper.UserMapper;
import wang.sunnly.mysql.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * UserService
 *
 * @author Sunnly
 * @since 2020/12/9 0009
 */
public interface UserService extends BaseService<UserMapper, User> {

    String getPassword(String username, String md5);

    UserInfo validate(HttpServletRequest request, String username, String password);

    int getUserCount(Long userId);

    void validateUser(Long userId);

    int getUsersCount(List<Long> userIds);

    void validateUsers(List<Long> userIds);

    List<Map<String, Object>> getMenuByUsername(String username);

    int insertUser(User entity);

    int deleteUser(User user);

    int deleteUsers(User users);

    int updateUsers(User user);

    int updateUserPwd(User user, String oldPwd, String password);
}


