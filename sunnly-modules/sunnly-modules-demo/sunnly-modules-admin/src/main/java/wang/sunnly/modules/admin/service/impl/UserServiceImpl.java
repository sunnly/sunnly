package wang.sunnly.modules.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import wang.sunnly.modules.admin.domain.Role;
import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.modules.admin.exception.UserAssertEnum;
import wang.sunnly.modules.admin.mapper.UserMapper;
import wang.sunnly.modules.admin.service.UserService;
import wang.sunnly.modules.api.entity.UserInfo;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/9 0009
 */
@Service
public class UserServiceImpl
        extends BaseServiceImpl<UserMapper, User>
        implements UserService, BaseService<UserMapper, User> {

    @Override
    public UserInfo validate(String username, String password) {
        UserAssertEnum.USERNAME_NOT_NULL.assertNotNull(username);
        User resUser = mapper.getUserByUsername(username);

        UserAssertEnum.USERNAME_PASSWORD_NOT_MATCH.assertEques(password, resUser.getPassword());
//        List<Role> roleList =
        return userToUserInfo(resUser);
    }

    private UserInfo userToUserInfo(User user) {
        if (user != null) {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(user, userInfo);
            return userInfo;
        }
        return null;
    }

}


