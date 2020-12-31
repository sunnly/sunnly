package wang.sunnly.modules.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
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
     * @param parentId 父机构ID
     * @param exclude 是否排除自己 1：排除自己，0：包含自己
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

    @Select("<script>" +
            "SELECT " +
            "group_id, group_code, group_name, group_fullname as fullName, group_parent_id, group_parent_name, \n" +
            "    group_ids, group_auth_ids, group_manager_id, group_manager_name, group_phone, group_mobile, \n" +
            "    group_desc, group_method, group_status, country_code, country_name, province_code, \n" +
            "    province_name, city_code, city_name, area_code, area_name, street_code, street_name, \n" +
            "    addr, `type`, group_order, create_time, create_user_id, create_user_name, create_user_ip, update_time,\n" +
            "    update_user_id, update_user_name, update_user_ip" +
            " FROM base_group " +
            "WHERE FIND_IN_SET(#{parentId},CONCAT(group_id,',',group_ids)) " +
            "<if test=\"exclude != 1\">" +
            "AND group_id != #{parentId}" +
            "</if>" +
            "</script>")
    @ResultMap("BaseResultMap")
    List<Map<String, Object>> query1(@Param("parentId") Long parentId, @Param("exclude") Integer exclude);

    @Select("<script>" +
            "SELECT " +
            "group_id, group_code, group_name, group_fullname as fullName, group_parent_id, group_parent_name, \n" +
            "    group_ids, group_auth_ids, group_manager_id, group_manager_name, group_phone, group_mobile, \n" +
            "    group_desc, group_method, group_status, country_code, country_name, province_code, \n" +
            "    province_name, city_code, city_name, area_code, area_name, street_code, street_name, \n" +
            "    addr, `type`, group_order, create_time, create_user_id, create_user_name, create_user_ip, update_time,\n" +
            "    update_user_id, update_user_name, update_user_ip" +
            " FROM base_group " +
            "WHERE FIND_IN_SET(#{parentId},CONCAT(group_id,',',group_ids)) " +
            "<if test=\"exclude != 1\">" +
            "AND group_id != #{parentId}" +
            "</if>" +
            "</script>")
//    @DataPermission("aaa")
    List<Map<String, Object>> query(@Param("parentId") Long parentId, @Param("exclude") Integer exclude);


}
