package wang.sunnly.modules.admin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import wang.sunnly.modules.admin.domain.GroupUser;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;

import java.util.List;

/**
 * GroupUserMapper
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface GroupUserMapper extends BaseMapper<GroupUser> {

    @Delete("DELETE FROM base_group_user WHERE user_id = #{userId}")
    int deleteGroupUser(@Param("userId") Long userId);


    @Delete("<script> " +
            "DELETE FROM base_group_user " +
            "WHERE user_id IN " +
            "<foreach collection=\"userIds\" item=\"userId\" index=\"index\" separator=\",\" open=\"(\" close=\")\"> " +
            "  #{userId}" +
            "</foreach>" +
            "</script>")
    int deleteGroupUsers(@Param("userIds") List<Long> userIds);

    @Insert("<script>" +
            "INSERT INTO base_group_user(group_user_id, group_id, user_id) VALUES " +
            "<foreach collection=\"entityList\" item=\"item\" index=\"index\" separator=\",\" > " +
            "( " +
            "#{item.groupUserId}, " +
            "#{item.groupId}, " +
            "#{item.userId} " +
            ") " +
            "</foreach>" +
            "</script>")
    int insertGroupUsers(@Param("entityList") List<GroupUser> entityList);

}
