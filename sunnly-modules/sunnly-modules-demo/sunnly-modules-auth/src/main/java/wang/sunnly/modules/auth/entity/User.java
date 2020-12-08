package wang.sunnly.modules.auth.entity;

import lombok.Data;

/**
 * User
 * 用户信息表
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@Data
public class User {

    private String username;
    private String password;
}
