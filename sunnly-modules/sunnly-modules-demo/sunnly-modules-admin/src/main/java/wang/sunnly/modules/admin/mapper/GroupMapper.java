package wang.sunnly.modules.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * GroupMapper
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface GroupMapper extends BaseMapper<Group> {

    /**
     * 获取子机构
     *
     * @param parentId 父机构ID
     * @param exclude  是否排除自己 1：排除自己，0：包含自己
     * @return 返回子机构列表
     */
    @Select("<script>" +
            "SELECT * FROM base_group " +
            "WHERE FIND_IN_SET(#{parentId},CONCAT(group_id,',',group_ids)) " +
            "<if test=\"exclude != 1\">" +
            "AND group_id != #{parentId}" +
            "</if>" +
            "</script>")
    @ResultMap("BaseResultMap")
    List<Group> getChildren(@Param("parentId") long parentId, @Param("exclude") int exclude);

    /**
     * 获取groupId条数，主要验证该机构部门是否存在
     *
     * @param groupId 机构ID
     * @return 返回机构、部门数量
     */
    @Select("SELECT COUNT(1) FROM base_group WHERE group_id =#{groupId}")
    int getGroupCount(@Param("groupId") Long groupId);

    /**
     * 查询这些机构、部门是否存在，主要判断机构部门来源是否合法
     *
     * @param groupIds 查询的机构、部门Id
     * @return 返回存在的用户数
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM base_group WHERE group_id IN " +
            "<foreach collection=\"groupIds\" item=\"groupId\" index=\"index\" separator=\",\" open=\"(\" close=\")\"> " +
            "  #{groupId}" +
            "</foreach>" +
            "</script>")
    int getGroupsCount(@Param("groupId") List<Long> groupIds);

    /**
     * 删除机构、部门（假删）
     *
     * @param group 删除机构
     * @return 返回删除条数
     */
    @Update("UPDATE base_group SET group_status='0',update_time=#{group.updateTime}," +
            "update_user_id=#{group.updateUserId},update_user_name=#{group.updateUserName}," +
            "update_user_ip=#{group.updateUserIp} where group_id=#{group.groupId}")
    int deleteGroup(@Param("group") Group group);

    /**
     * 批量删除机构部门(假删)
     *
     * @param groups 机构
     * @return 返回影响条数
     */
    @Update("<script> " +
            "UPDATE base_group SET status='0',update_time=#{groups.updateTime}," +
            "update_user_id=#{groups.updateUserId},update_user_name=#{groups.updateUserName}," +
            "update_user_ip=#{groups.updateUserIp} WHERE group_id IN " +
            "<foreach collection=\"groups.groupIds\" item=\"groupId\" index=\"index\" separator=\",\" open=\"(\" close=\")\"> " +
            "  #{groupId}" +
            "</foreach>" +
            "</script>")
    int deleteGroups(@Param("groups") Group groups);

    /**
     * 根据部门ID获取部门下的所有用户
     *
     * @param groupIds 部门列表
     * @return 返回机构用户ID
     */
    @Select("<script> " +
            "SELECT group_user_id as groupUserId, group_id as groupId, user_id as userId FROM base_group_user WHERE group_id IN " +
            "<foreach collection=\"groupIds\" item=\"groupId\" index=\"index\" separator=\",\" open=\"(\" close=\")\"> " +
            "  #{groupId}" +
            "</foreach>" +
            "</script>")
    List<Map<String, Long>> getUserGroupByGroupIds(@Param("groupIds") List<Long> groupIds);

    /**
     * 根据部门ID获取部门下的所有用户
     *
     * @param groupIds 部门列表
     * @return 返回机构用户ID
     */
    @Select("<script> " +
            "DELETE FROM base_group_user WHERE group_id IN " +
            "<foreach collection=\"groupIds\" item=\"groupId\" index=\"index\" separator=\",\" open=\"(\" close=\")\"> " +
            "  #{groupId}" +
            "</foreach>" +
            "</script>")
    List<Map<String, Long>> deleteUserGroupByGroupIds(@Param("groupIds") List<Long> groupIds);


}
