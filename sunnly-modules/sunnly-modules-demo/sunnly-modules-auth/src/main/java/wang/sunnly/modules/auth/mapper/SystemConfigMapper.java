package wang.sunnly.modules.auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import wang.sunnly.modules.auth.domain.SystemConfig;
import wang.sunnly.tk.mybatis.mapper.BaseMapper;

public interface SystemConfigMapper extends BaseMapper<SystemConfig> {

//    @Select("SELECT sc_id, sc_account FROM `auth_system_config`")
//    @Results(id="BaseResultMap1" ,value = {
//            @Result(property = "scId",column = "sc_id"),
//            @Result(property = "scAccount",column = "sc_account"),
//            @Result(property = "scSystemChannel",column ="sc_system_channel")
//    })
//    public List<SystemConfig> provinceName();

    @Select("SELECT * FROM auth_system_config WHERE sc_system_channel=#{channel}")
    @ResultMap("BaseResultMap")
    SystemConfig getSysConfigByChannel(@Param("channel") String channel);
}
