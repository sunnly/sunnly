package wang.sunnly.modules.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import wang.sunnly.modules.admin.domain.Role;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * RoleMapper
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT r.role_id AS roleId,r.role_name AS roleName, IF(gur.user_id=#{userId} ,'1','0') AS self " +
            "FROM base_group_user_role gur, " +
            "base_role r " +
            "WHERE (FIND_IN_SET(gur.group_id ,  " +
            "(SELECT group_ids FROM base_user u, base_group_user gu, base_group g " +
            "WHERE u.user_id = gu.user_id " +
            "AND g.group_id = gu.group_id " +
            "AND u.user_id=#{userId} " +
            "))\n" +
            "AND r.role_id=gur.role_id) " +
            "OR gur.user_id = #{userId} ")
    List<Map<String, Object>> getRolesByUserId(@Param("userId") Long userId);


    @Select("SELECT COUNT(1) FROM base_role WHERE role_id=#{roleId}")
    int getRoleCount(@Param("roleId") Long roleId);

    @Select("<script>" +
            "SELECT COUNT(1) FROM base_role WHERE role_id IN " +
            "<foreach collection=\"roleIds\" item=\"roleId\" index=\"index\" separator=\",\" open=\"(\" close=\")\"> " +
            "  #{roleId}" +
            "</foreach>" +
            "</script>")
    int getRolesCount(@Param("roleIds") List<Long> roleIds);

    @Update("UPDATE base_role SET role_status='0',update_time=#{role.updateTime}, " +
            "update_user_id=#{role.updateUserId},update_user_name=#{role.updateUserName}, " +
            "update_user_ip=#{role.updateUserIp} where role_id=#{role.roleId}")
    int deleteRole(@Param("role") Role role);

    @Update("UPDATE base_role SET role_code=#{role.roleCode}, role_name=#{role.roleName}, " +
            "role_desc=#{role.roleDesc}, update_user_id=#{role.updateUserId}," +
            "update_user_name=#{role.updateUserName}, update_user_ip=#{role.updateUserIp} " +
            "WHERE role_id=#{role.roleId}")
    int updateRole(@Param("role") Role role);
}
