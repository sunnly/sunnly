package wang.sunnly.modules.admin.mapper;

import org.apache.ibatis.annotations.Param;
import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;
/**
 * UserMapper
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface UserMapper extends BaseMapper<User> {

    User getUserByUsername(@Param("username") String username);
}