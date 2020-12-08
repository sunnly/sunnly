package wang.sunnly.modules.api.entity;

import lombok.Data;
import wang.sunnly.common.core.security.jwt.entity.IJwtInfo;

/**
 * JwtUserInfo
 * JWT中封装的用户信息
 *
 * @author Sunnly
 * @since 2020/12/8 0008
 */
@Data

public class JwtUserInfo extends UserInfo implements IJwtInfo {
    private String userId;
    private String username;
    private String name;
    private double height;
    private String dept;
    private int age;
    private String role;

    @Override
    public String getUniquename() {
        return username;
    }

    @Override
    public String getId() {
        return userId;
    }

}
