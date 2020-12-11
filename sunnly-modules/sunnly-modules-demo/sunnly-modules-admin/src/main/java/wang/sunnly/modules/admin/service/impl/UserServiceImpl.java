package wang.sunnly.modules.admin.service.impl;

import com.alibaba.nacos.common.utils.Md5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.modules.admin.exception.UserAssertEnum;
import wang.sunnly.modules.admin.mapper.UserMapper;
import wang.sunnly.modules.admin.service.UserService;
import wang.sunnly.modules.api.entity.UserInfo;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

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

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public String getPassword(String username, String md5){
        return encoder.encode(username+":"+md5);
    }

    @Override
    public UserInfo validate(String username, String password) {
        UserAssertEnum.USERNAME_NOT_NULL.assertNotNull(username);
        User resUser = mapper.getUserByUsername(username);

        UserAssertEnum.USERNAME_PASSWORD_NOT_MATCH
                .assertIsTrue(encoder
                        .matches(username+":"+password, resUser.getPassword()));

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

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        String md5 = Md5Utils.getMD5("admin", "utf-8");
        System.out.println(md5);
        System.out.println(userService.encoder.encode("admin:"+md5));
        System.out.println(userService.encoder.matches("admin:"+md5,
                "$2a$12$8HxNsVDKIZpJWgxgcaRSVuoQ4ARlHkkzQ5agRtQ2uiOf82f2jTc4S"));

        System.out.println(userService.encoder.matches("admin:21232f297a57a5a743894a0e4a801fc3",
        "$2a$12$eOs3.6FZxFECpEBEOcdnyOyJr3fxigtrOBab7bOzTCceis0HKS7WG"));
    }
}

