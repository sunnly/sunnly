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

    @Select("select * from company where id = #{id}")
    Company findById(@Param("id") Integer id);

    @Select("select * from company")
    List<Company> findList();

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

    @Insert("insert into company(ID,NAME,AGE,ADDRESS,SALARY) " +
            "values (#{id},#{name},#{age},#{address},#{salary})")
    int insert(@Param("company") Company company);

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

    @Delete("delete from company where id = #{id}")
    int delete(@Param("id") Integer id);

}
