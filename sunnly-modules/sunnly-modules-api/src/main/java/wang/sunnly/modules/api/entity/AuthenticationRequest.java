package wang.sunnly.modules.api.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * AuthenticationRequest
 * 用户登录对象
 *
 * @author Sunnly
 * @since 2020/12/8 0008
 */
@Data
public class AuthenticationRequest implements Serializable {

    /**
     * 系统渠道
     */
    private String channel;
    /**
     * 登录用户名
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;

    /**
     * 验证码主键
     */
    private String codeId;
    /**
     * 登录验证码
     */
    private String code;

    public String getCodeId(){
        if (StringUtils.isEmpty(codeId)){
            codeId = username;
        }
        return codeId;
    }

}
