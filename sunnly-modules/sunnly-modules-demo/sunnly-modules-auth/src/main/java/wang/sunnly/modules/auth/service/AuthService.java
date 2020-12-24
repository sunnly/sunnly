package wang.sunnly.modules.auth.service;

/**
 * AuthService
 *
 * @author Sunnly
 * @since 2020/12/11
 */
public interface AuthService {
    /**
     * 获取锁定时长
     * @param id id
     * @return 返回锁定时长
     */
    long lockedTime(String id);

    /**
     * 锁定用户
     * @param id ID
     */
    void lockedUser(String id);

    /**
     * 获取登录Token
     * @param username 用户名
     * @param password 密码
     * @return 返回Token
     */
    String login(String username, String password);
}
