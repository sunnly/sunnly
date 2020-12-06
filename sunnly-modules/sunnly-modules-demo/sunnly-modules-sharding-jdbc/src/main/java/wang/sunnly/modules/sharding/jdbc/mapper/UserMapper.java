package wang.sunnly.modules.sharding.jdbc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import wang.sunnly.modules.sharding.jdbc.entity.User;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;

/**
 * UserMapper
 * 用户Mapper
 *
 * @author Sunnly
 * @since 2020/12/3
 */
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from t_user where id=${id}")
//    public User selectById(@Param("id") Integer id);


    @Override
    @Insert("insert into t_user(name,age) values(#{user.name},#{user.age})")
    @Options(useGeneratedKeys=true, keyProperty="user.id", keyColumn="id")
    int insert(@Param("user") User entity);

    @Insert("insert into t_user(name,age) values(#{user.name},#{user.age})")
    @Options(useGeneratedKeys=true, keyProperty="user.id", keyColumn="id")
    int insertUser(@Param("user") User user);
}
