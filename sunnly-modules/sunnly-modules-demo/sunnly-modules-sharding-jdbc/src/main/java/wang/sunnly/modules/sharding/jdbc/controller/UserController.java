package wang.sunnly.modules.sharding.jdbc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.modules.sharding.jdbc.entity.User;
import wang.sunnly.modules.sharding.jdbc.service.UserService;
import wang.sunnly.mysql.controller.BaseController;

/**
 * UserController
 * 用户Controller
 *
 * @author Sunnly
 * @since 2020/12/3
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserService, User> {

}
