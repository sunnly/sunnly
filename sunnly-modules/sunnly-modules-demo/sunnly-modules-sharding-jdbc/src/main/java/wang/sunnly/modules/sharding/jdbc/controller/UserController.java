package wang.sunnly.modules.sharding.jdbc.controller;


import org.springframework.web.bind.annotation.*;
import wang.sunnly.common.core.utils.SnowFlake;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.sharding.jdbc.entity.User;
import wang.sunnly.modules.sharding.jdbc.service.UserService;
import wang.sunnly.mysql.controller.BaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @PostMapping("add")
    public ObjectResponse<User> add1(@RequestBody User user){
        SnowFlake snowFlake = new SnowFlake(5, 6);
        user.setId(snowFlake.nextId(false));
        return new ObjectResponse<User>(service.insert(user) == 1 ? user : null);
    }

    @PostMapping("add2")
    public ObjectResponse<User> add2(@RequestBody User user){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ObjectResponse<User>(service.insertUser(user) == 1 ? user : null);
    }

    @GetMapping("batch")
    public ListResponse<User> batchAdd(){
        List<User> list = new ArrayList<>(16);
        SnowFlake snowFlake = new SnowFlake(5, 6);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(snowFlake.nextId(false));
            user.setName("sunnly_"+i);
            user.setAge(random.nextInt(20)+20);
            list.add(user);
            service.insert(user);
        }
        return new ListResponse<>(list);
    }
}
