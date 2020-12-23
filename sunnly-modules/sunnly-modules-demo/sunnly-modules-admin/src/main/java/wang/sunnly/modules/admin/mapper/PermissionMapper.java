package wang.sunnly.modules.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wang.sunnly.modules.admin.domain.Permission;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * PermissionMapper
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("<script>" +
            "SELECT rule.prule_id as id,rule.prule_rule as rule,prule_permit as permit " +
            "FROM base_permission_rule AS rule " +
            "LEFT JOIN base_permission_relations AS rel ON (rule.prule_id=rel.prule_id) " +
            "WHERE permission_code='abc' " +
            "AND (1=2 " +

            "<if test=\"userId != null and userId !=''\">" +
            "OR (pr_external_type='1' AND pr_external_id=#{userId}) " +
            "</if>" +

            "<if test=\"dept != null and dept !=''\">" +
            "OR (pr_external_type='2' AND pr_external_id=#{dept}) " +
            "</if>" +

            "<if test=\"org != null and org !=''\">" +
            "OR (pr_external_type='3' AND pr_external_id=#{org}) " +
            "</if>" +

            "<if test=\"roles != null and roles.size()>0\">" +
            "OR (pr_external_type='4' AND pr_external_id in " +
            "<foreach collection=\"roles\" item=\"role\" index=\"index\" open=\"(\" close=\")\" separator=\",\">" +
            "#{role}" +
            "</foreach>" +
            ") " +
            "</if>" +

            ")" +
            "AND rule.prule_type=#{type} " +
            "AND rule.prule_status='1' " +
            "ORDER BY pr_external_type ASC, pr_order DESC" +
            "</script>")
    List<Map<String,String>> getPermission(@Param("userId") String userId, @Param("dept") String dept,
                                           @Param("org") String org, @Param("roles") List<String> roles,
                                           @Param("permissionCode") String permissionCode, @Param("type") int type);

}