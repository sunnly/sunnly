package wang.sunnly.modules.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.nacos.common.utils.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wang.sunnly.common.api.entity.UserInfo;
import wang.sunnly.common.api.exception.UserAssertEnum;
import wang.sunnly.common.core.utils.DateUtils;
import wang.sunnly.common.core.utils.SnowFlake;
import wang.sunnly.common.web.utils.IpUtils;
import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.modules.admin.domain.Resources;
import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.modules.admin.mapper.UserMapper;
import wang.sunnly.modules.admin.service.*;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Resource
    private ResourcesService resourcesService;

    @Resource
    private RoleService roleService;

    @Resource
    private GroupService groupService;

    @Resource
    private GroupUserService groupUserService;

    @Resource
    private SnowFlake snowFlake;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public String getPassword(String username, String md5){
        return encoder.encode(username+":"+md5);
    }

    private void matchesPwd(String username, String password, String dbPassword){
        UserAssertEnum.USERNAME_PASSWORD_NOT_MATCH
                .assertIsTrue(encoder
                        .matches(username+":"+password, dbPassword));
    }

    @Override
    public UserInfo validate(HttpServletRequest request, String username, String password) {
        UserAssertEnum.USERNAME_NOT_NULL.assertNotNull(username);
        User resUser = mapper.getUserByUsername(username);
        UserAssertEnum.USERNAME_PASSWORD_NOT_MATCH.assertNotNull(resUser);
        matchesPwd(username, password, resUser.getPassword());

        return userToUserInfo(resUser,IpUtils.getIpAddr(request));
    }

    @Override
    public int getUserCount(Long userId){
        return mapper.getUserCount(userId);
    }

    @Override
    public int getUsersCount(List<Long> userIds){
        return mapper.getUsersCount(userIds);
    }

    @Override
    public void validateUser(Long userId){
        UserAssertEnum.USER_ID_NOT_EXIST.assertEquals(getUserCount(userId),1);
    }

    @Override
    public void validateUsers(List<Long> userIds){
        UserAssertEnum.USER_ID_NOT_EXIST.assertEquals(getUsersCount(userIds),userIds.size());
    }

    private UserInfo userToUserInfo(User user,String ip) {
        if (user != null) {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(user, userInfo);
            userInfo.setUserId(user.getUserId()+"");
            userInfo.setLoginIp(ip);
            userInfo.setLoginTime(DateUtils.dbCreateTime());
            //TODO 获取角色列表
            List<Map<String, Object>> rolesByUserId = roleService.getRolesByUserId(user.getUserId());
            userInfo.setRoles(rolesByUserId+"");
//            userInfo.setDept(dept);
//            userInfo.setOrg(org);
            return userInfo;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getMenuByUsername(String username){
        List<Resources> menuByUsername = resourcesService.getMenuByUsername(username);
        List<Map<String,Object>> list = new ArrayList<>(16);
        menuByUsername.stream().forEach(menu -> list.add(BeanUtil.beanToMap(menu)));
        return list;
    }

    @Override
    public int insertUser(User entity) {
        UserAssertEnum.USERNAME_NOT_NULL.assertNotNull(entity.getUsername());
        //查询用户是否存在
        User userByUsername = mapper.getUserByUsername(entity.getUsername());
        UserAssertEnum.USERNAME_EXIST.assertNull(userByUsername);
        long userId = snowFlake.nextId();
        entity.setUserId(userId);
        String password = entity.getPassword();
        if (StringUtils.isNotEmpty(password)){
            password = Md5Utils.getMD5("123456","utf-8");
        }
        entity.setPassword(getPassword(entity.getUsername(), password));
        entity.setChannel("sysbase");
        entity.setUserStatus("1");
        Long deptId = entity.getDeptId();
        //关联用户部门
        if (deptId != null && deptId != 0){
            Group group = groupService.selectById(deptId);
            UserAssertEnum.USER_DEPT_ERROR.assertNotNull(group);
            groupUserService.insertGroupUser(group.getGroupId(), entity.getUserId());
        }
        return mapper.insert(entity);
    }

    @Override
    public int deleteUser(User user){
        return mapper.deleteUser(user);
    }
    @Override
    public int deleteUsers(User users){
        return mapper.deleteUsers(users);
    }

    @Override
    public int updateUsers(User user){
        User dbUser = mapper.selectByPrimaryKey(user.getUserId());
        UserAssertEnum.USER_ID_NOT_EXIST.assertNotNull(dbUser);
        // 根据需求重新赋值对象
        dbUser.setAddr(user.getAddr());
        dbUser.setName(user.getName());
        dbUser.setEmail(user.getEmail());
        dbUser.setIdcard(user.getIdcard());
        return mapper.updateByPrimaryKey(dbUser);
    }

    @Override
    public int updateUserPwd(User user, String oldPwd, String password){
        //验证用户
        matchesPwd(user.getUsername(), oldPwd, user.getPassword());
        user.setPassword(getPassword(user.getUsername(), password));
        return mapper.updateUserPwd(user);
    }
//    public static void main(String[] args) {
//        UserServiceImpl userService = new UserServiceImpl();
//        String md5 = Md5Utils.getMD5("admin", "utf-8");
//        System.out.println(md5);
//        System.out.println(userService.encoder.encode("admin:"+md5));
//        System.out.println(userService.encoder.matches("admin:"+md5,
//                "$2a$12$8HxNsVDKIZpJWgxgcaRSVuoQ4ARlHkkzQ5agRtQ2uiOf82f2jTc4S"));
//
//        System.out.println(userService.encoder.matches("admin:21232f297a57a5a743894a0e4a801fc3",
//                "$2a$12$eOs3.6FZxFECpEBEOcdnyOyJr3fxigtrOBab7bOzTCceis0HKS7WG"));
//    }

    public static void main(String[] args) {
        System.out.println(Md5Utils.getMD5("123456","utf-8"));

    }
}

