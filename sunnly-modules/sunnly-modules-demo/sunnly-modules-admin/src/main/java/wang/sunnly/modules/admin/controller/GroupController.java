package wang.sunnly.modules.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.core.security.jwt.utils.JwtUtil;
import wang.sunnly.common.web.exception.enums.ArgumentResponseEnum;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.domain.Group;
import wang.sunnly.modules.admin.exception.UserAssertEnum;
import wang.sunnly.modules.admin.service.GroupService;
import wang.sunnly.modules.api.entity.JwtUserInfo;
import wang.sunnly.mysql.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * GroupController
 * 机构管理
 *
 * @author Sunnly
 * @since 2020/12/10 0010
 */
@RestController
@RequestMapping("group")
@Slf4j
public class GroupController extends BaseController<GroupService, Group> {

    @Value("${macro.jwt.user.token-header}")
    private String tokenHeader;
    @Value("${macro.jwt.user.secret}")
    private String secret;

    @PostMapping("")
    public ObjectResponse<Group> addGroup(@RequestBody Group entity, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);

        // 获取jwt中的用户信息
        UserAssertEnum.USER_TOKEN_NOT_EMPTY.assertNotNull(token);

        JwtUserInfo jwtInfo = null;
        try {
            JwtUtil jwtUtil = new JwtUtil();
            jwtInfo = (JwtUserInfo) jwtUtil.getJwtInfo(JwtUserInfo.class, token, secret);
        } catch (Exception e) {
            log.error("token 解析异常:"+e.getMessage());
            ArgumentResponseEnum.TOKEN_INVALID.assertFail(e.getMessage());
        }
        ArgumentResponseEnum.TOKEN_NOT_NULL.assertNotNull(jwtInfo);
        String userId = jwtInfo.getId();
        String username = jwtInfo.getUsername();
        String loginIp = jwtInfo.getLoginIp();
//        String loginTime = jwtInfo.getLoginTime()
        return new ObjectResponse<Group>(service.insertGroup(entity, Long.valueOf(userId), username, loginIp) == 1 ? entity : null);
    }
}
