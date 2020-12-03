package wang.sunnly.modules.sharding.jdbc.mapper;

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
}
