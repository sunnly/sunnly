package wang.sunnly.modules.api.entity;

import lombok.Data;

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
     * 登录用户名
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 登录验证码
     */
    private String code;

}
