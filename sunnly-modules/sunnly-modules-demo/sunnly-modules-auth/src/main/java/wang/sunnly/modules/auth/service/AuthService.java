package wang.sunnly.modules.auth.service;

/**
 * AuthService
 *
 * @author Sunnly
 * @since 2020/12/11
 */
public interface AuthService {

    long lockedTime(String username);

    void lockedUser(String username);

    void setValidateCode(String username, String code);

    String getValidateCode(String username);

    void removeValidateCode(String username);
    /**
     * 获取登录Token
     * @param username 用户名
     * @param password 密码
     * @return 返回Token
     */
    String login(String username, String password);
}
