package wang.sunnly.modules.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;

import java.util.List;

/**
 * UserMapper
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 返回用户信息
     */
    @Select("select * from base_user where username=#{username}")
    @ResultMap("BaseResultMap")
    User getUserByUsername(@Param("username") String username);

    /**
     * 获取userId条数，主要验证该用户是否存在
     * @param userId 用户ID
     * @return 返回用户数量
     */
    @Select("SELECT COUNT(1) FROM base_user WHERE user_id =#{userId}")
    int getUserCount(@Param("userId") Long userId);

    /**
     * 查询这些用户是否存在，主要判断用户来源是否合法
     * @param userIds 查询的用户Id
     * @return 返回存在的用户数
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM base_user WHERE user_id IN " +
            "<foreach collection=\"userIds\" item=\"userId\" index=\"index\" separator=\",\" open=\"(\" close=\")\"> " +
            "  #{userId}" +
            "</foreach>" +
            "</script>")
    int getUsersCount(@Param("userIds") List<Long> userIds);

    /**
     * 删除用户（假删）
     * @param user 删除用户对象
     * @return 返回删除用户条数
     */
    @Update("UPDATE base_user SET user_status='0',update_time=#{user.updateTime}, " +
            "update_user_id=#{user.updateUserId},update_user_name=#{user.updateUserName}, " +
            "update_user_ip=#{user.updateUserIp} where user_id=#{user.userId}")
    int deleteUser(@Param("user") User user);

    /**
     * 批量删除用户(假删)
     * @param users 删除用户组，用户ID封装到userIds中
     * @return 返回删除用户数量
     */
    @Update("<script> " +
            "UPDATE base_user SET user_status='0',update_time=#{users.updateTime}," +
            "update_user_id=#{users.updateUserId},update_user_name=#{users.updateUserName}," +
            "update_user_ip=#{users.updateUserIp} WHERE user_id IN " +
            "<foreach collection=\"users.userIds\" item=\"userId\" index=\"index\" separator=\",\" open=\"(\" close=\")\"> " +
            "  #{userId}" +
            "</foreach>" +
            "</script>")
    int deleteUsers(@Param("users") User users);

    /**
     * 修改密码
     * @param user 用户信息
     * @return 返回影响条数
     */
    @Update("UPDATE base_user SET password=#{user.password}, update_time=#{user.updateTime}, " +
            "update_user_id=#{user.updateUserId},update_user_name=#{user.updateUserName}," +
            "update_user_ip=#{user.updateUserIp} WHERE user_id=#{user.userId}")
    int updateUserPwd(@Param("user") User user);

}
