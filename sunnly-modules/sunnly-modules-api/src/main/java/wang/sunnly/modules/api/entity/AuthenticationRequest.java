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

    private String username;
    private String password;
    private String code;
    private String method;

}
