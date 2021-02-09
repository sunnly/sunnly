package wang.sunnly.modules.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import wang.sunnly.modules.admin.domain.Resources;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;

import java.util.List;

/**
 * ResourcesMapper
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface ResourcesMapper extends BaseMapper<Resources> {

    @Select("SELECT DISTINCT res.* FROM base_user u " +
            "LEFT JOIN base_group_user g ON (u.user_id = g.user_id) " +
            "LEFT JOIN base_group_user_role gur ON(FIND_IN_SET(g.group_id , gur.group_ids) OR u.user_id = gur.user_id) " +
            "LEFT JOIN base_role r ON(gur.role_id = r.role_id) " +
            "LEFT JOIN base_resources_role rr ON(rr.role_id = r.role_id) " +
            "LEFT JOIN base_resources res ON(rr.res_id = res.res_id ) " +
            "WHERE u.username = #{username}")
    @ResultMap("BaseResultMap")
    List<Resources> getMenuByUsername(@Param("username") String username);

}
