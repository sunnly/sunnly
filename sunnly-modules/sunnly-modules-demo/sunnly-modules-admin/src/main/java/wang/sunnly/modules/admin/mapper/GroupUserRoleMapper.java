package wang.sunnly.modules.admin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import wang.sunnly.modules.admin.domain.GroupUserRole;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;

import java.util.List;

/**
 * GroupUserRoleMapper
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface GroupUserRoleMapper extends BaseMapper<GroupUserRole> {

    /**
     * 删除该用户下的所有角色
     * @param userId 用户ID
     */
    @Delete("DELETE FROM base_group_user_role WHERE user_id=#{userId} ")
    int deleteByUserId(@Param("userId") Long userId);


    /**
     * 删除该用户下的所有角色
     * @param userIds 用户IDs
     */
    @Delete("<script> " +
            "DELETE FROM base_group_user_role " +
            "WHERE user_id IN " +
            "<foreach collection=\"userIds\" item=\"userId\" index=\"index\" separator=\",\" open=\"(\" close=\")\"> " +
            "  #{userId}" +
            "</foreach>" +
            "</script>")
    int deleteByUserIds(@Param("userId") List<Long> userIds);

    @Insert("<script> " +
            "INSERT INTO base_group_user_role( " +
            "group_user_role_id,group_id,group_ids,user_id," +
            "role_id,create_time,create_user_id,create_user_name,create_user_ip)  " +
            "VALUES " +

            "<foreach collection=\"groupUserRoleList\" item=\"item\" index=\"index\" separator=\",\" > " +
            "( " +
            "#{item.groupUserRoleId}, " +
            "#{item.groupId}, " +
            "#{item.groupIds}, " +
            "#{item.userId}, " +
            "#{item.roleId}, " +
            "#{item.createTime}, " +
            "#{item.createUserId}, " +
            "#{item.createUserName}, " +
            "#{item.createUserIp} " +
            ") " +
            "</foreach>" +

            "</script>")
    int insertUserRoles(@Param("groupUserRoleList") List<GroupUserRole> groupUserRoleList);

    @Delete("<script> " +
            "DELETE FROM base_group_user_role " +
            "WHERE user_id=#{userId} AND role_id IN " +
            "<foreach collection=\"roleIds\" item=\"roleId\" index=\"index\" separator=\",\" open=\"(\" close=\")\"> " +
            "  #{roleId}" +
            "</foreach>" +
            "</script>")
    int deleteExists(Long userId, List<Long> roleIds);

    @Delete("DELETE FROM base_group_user_role WHERE user_id=#{userId} AND role_id =#{roleId}")
    int deleteExist(Long userId, Long roleId);
}
