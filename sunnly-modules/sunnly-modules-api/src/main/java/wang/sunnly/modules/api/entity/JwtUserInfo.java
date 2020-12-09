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

    @Override
    public String getUniquename() {
        return this.getUsername();
    }

    @Override
    public String getId() {
        return this.getUserId();
    }

}
