package wang.sunnly.modules.sqlite.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import wang.sunnly.modules.sqlite.entity.Company;

import java.util.List;

/**
 * CompanyMapper
 *
 * @author Sunnly
 * @since 2020/12/6 0006
 */
public interface CompanyMapper {

    /**
     * 根据ID查询
     * @param id ID
     * @return 返回查询结果
     */
    @Select("select * from company where id = #{id}")
    Company findById(@Param("id") Integer id);

    /**
     * 查询列表
     * @return 返回列表
     */
    @Select("select * from company")
    List<Company> findList();

    /**
     * 根据条件查询
     * @param company 查询条件
     * @return 返回查询列表
     */
    @Select("<script>" +
            "select * from company " +
            "where 1 = 1 " +
            "<if test=\"id != null\"> " +
            "    and id = #{id} " +
            "</if> " +
            "<if test=\"name != null\"> " +
            "    and name = #{name} " +
            "</if> " +
            "<if test=\"age != null\"> " +
            "    and age = #{age} " +
            "</if> " +
            "<if test=\"address != null\"> " +
            "    and address = #{address} " +
            "</if> " +
            "<if test=\"salary != null\"> " +
            "    and salary = #{salary} " +
            "</if>" +
            "</script>")
    List<Company> findListByCondition(@Param("company") Company company);

    /**
     * 插入
     * @param company 根据对象插入
     * @return 返回影响数量
     */
    @Insert("insert into company(ID,NAME,AGE,ADDRESS,SALARY) " +
            "values (#{id},#{name},#{age},#{address},#{salary})")
    int insert(@Param("company") Company company);

    /**
     * 根据条件修改
     * @param company 条件
     * @return 返回影响的记录数
     */
    @Update("<script>" +
            "update company " +
            "<set> " +
            "    <if test=\"name != null\"> " +
            "        name = #{name} " +
            "    </if> " +
            "    <if test=\"age != null\"> " +
            "        age = #{age} " +
            "    </if> " +
            "    <if test=\"address != null\"> " +
            "        address = #{address} " +
            "    </if> " +
            "    <if test=\"salary != null\"> " +
            "        salary = #{salary} " +
            "    </if> " +
            "</set> " +
            "where id = #{id}" +
            "</script>")
    int update(@Param("company") Company company);

    /**
     * 根据ID删除
     * @param id ID
     * @return 返回影响的记录数
     */
    @Delete("delete from company where id = #{id}")
    int delete(@Param("id") Integer id);

}
