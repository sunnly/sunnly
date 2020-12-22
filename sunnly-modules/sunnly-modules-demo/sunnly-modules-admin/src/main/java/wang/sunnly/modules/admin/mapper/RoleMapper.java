package wang.sunnly.modules.admin.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import wang.sunnly.modules.admin.domain.Role;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;

import java.util.List;
/**
 * RoleMapper
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from base_user")
    List<String> getRolesByUserId(Long userId);

}