package wang.sunnly.modules.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.nacos.common.utils.Md5Utils;
import org.springframework.web.bind.annotation.*;
import wang.sunnly.common.api.entity.FrontUserInfo;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.common.api.exception.UserAssertEnum;
import wang.sunnly.common.web.msg.result.ListResponse;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.admin.domain.GroupUserRole;
import wang.sunnly.modules.admin.domain.User;
import wang.sunnly.modules.admin.service.GroupUserRoleService;
import wang.sunnly.modules.admin.service.GroupUserService;
import wang.sunnly.modules.admin.service.UserService;
import wang.sunnly.mysql.controller.BaseController;
import wang.sunnly.security.ignore.annotation.IgnoreAuthToken;
import wang.sunnly.security.ignore.annotation.IgnoreClientToken;
import wang.sunnly.security.ignore.annotation.IgnoreUserToken;
import wang.sunnly.security.server.api.service.MacroTokenDomainService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * UserController
 * 用户信息获取
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@RestController
@RequestMapping("user")

public class UserController extends BaseController<UserService, User>  {

    @Resource
    private MacroTokenDomainService macroTokenDomainService;

    @Resource
    private GroupUserService groupUserService;

    @Resource
    private GroupUserRoleService groupUserRoleService;


    // ***********************************用户更改部门****************************************

    /**
     * 用户更改部门
     *
     * @param deptId 部门Id
     * @return 返回是否更改成功
     */
    @PutMapping("/dept/{deptId}/{userId}")
    @IgnoreClientToken
    public ObjectResponse<Boolean> addDeptForUser(@PathVariable("deptId") Long deptId, @PathVariable("userId") Long userId){
        return new ObjectResponse<>(groupUserService.insertGroupUser(deptId, userId)==1);
    }

    // ***********************************用户关联角色模块****************************************

    /**
     * 给用户重新分配角色,删除原有的角色
     *
     * @return 返回是否分配成功
     */
    @PutMapping("/roles")
    @IgnoreClientToken
    public ObjectResponse<Boolean> reallocateRole2User(@RequestBody GroupUserRole groupUserRole, HttpServletRequest request){
        macroTokenDomainService.setCreateInfo(request, groupUserRole);
        return new ObjectResponse<>(groupUserRoleService.reallocateRole2User(groupUserRole) > 0);
    }

    /**
     * 给用户重新分配角色，保留原有的角色
     *
     * @return 返回是否分配成功
     */
    @PatchMapping("/roles")
    @IgnoreClientToken
    public ObjectResponse<Boolean> addRole2User(@RequestBody GroupUserRole groupUserRole, HttpServletRequest request){
        macroTokenDomainService.setCreateInfo(request, groupUserRole);
        return new ObjectResponse<>(groupUserRoleService.addRole2User(groupUserRole) > 0);
    }

    /**
     * 将用户和角色进行关联
     *
     * @return 返回是否分配成功
     */
    @PostMapping("/roles")
    @IgnoreClientToken
    public ObjectResponse<Boolean> setRole2User(@RequestBody GroupUserRole groupUserRole, HttpServletRequest request){
        macroTokenDomainService.setCreateInfo(request, groupUserRole);
        return new ObjectResponse<>(groupUserRoleService.setRole2User(groupUserRole) > 0);
    }

    /**
     * 将用户和角色解除关系
     *
     * @return 返回是否分配成功
     */
    @DeleteMapping("/roles")
    @IgnoreClientToken
    public ObjectResponse<Boolean> deleteRole2User(@RequestBody GroupUserRole groupUserRole){
        return new ObjectResponse<>(groupUserRoleService.deleteRole2User(groupUserRole) > 0);
    }

    // ***********************************用户模块****************************************

    /**
     * 新增用户
     *
     * @param entity 用户实体类
     * @param request 响应
     * @return 返回新增用户
     */
    @PostMapping
    @IgnoreClientToken
    public ObjectResponse<User> addUser(@RequestBody User entity, HttpServletRequest request){
        macroTokenDomainService.setCreateInfo(request, entity);
        int i = service.insertUser(entity);
        entity.setPassword(null);
        return new ObjectResponse<>(i == 1 ? entity : null);
    }

    /**
     * 删除用户
     * @param userId 用户ID
     * @param request 响应
     * @return 返回是否删除成功
     */
    @DeleteMapping("{userId}")
    @IgnoreClientToken
    public ObjectResponse<Boolean> deleteUser(@PathVariable("userId") Long userId, HttpServletRequest request){
        User user = service.selectById(userId);
        macroTokenDomainService.setUpdateInfo(request, user);
        return new ObjectResponse<>(service.deleteUser(user) == 1);
    }

    /**
     * 批量删除用户
     * @param users 用户
     * @param request 响应
     * @return 返回是否删除成功
     */
    @DeleteMapping
    @IgnoreClientToken
    public ObjectResponse<Boolean> deleteUsers(@RequestBody User users, HttpServletRequest request){
        macroTokenDomainService.setUpdateInfo(request, users);
        return new ObjectResponse<>(service.deleteUsers(users) > 0);
    }

    /**
     * 修改用户
     * @param user 用户信息
     * @param request 响应
     * @return 返回是否修改成功
     */
    @PutMapping
    @IgnoreClientToken
    public ObjectResponse<Boolean> updateUsers(@RequestBody User user, HttpServletRequest request){
        macroTokenDomainService.setUpdateInfo(request, user);
        return new ObjectResponse<>(service.updateUsers(user) > 0);
    }

    /**
     * 修改用户密码
     * @param userId 用户Id
     * @param oldPwd 旧密码
     * @param password 新密码
     * @param request 响应
     * @return 返回是否修改成功
     */
    @PutMapping("pwd/{userId}")
    @IgnoreClientToken
    public ObjectResponse<Boolean> updateUserPwd(@PathVariable("userId") Long userId, @RequestParam String oldPwd, @RequestParam String password, HttpServletRequest request){
        User user = service.selectById(userId);
        UserAssertEnum.USERNAME_NOT_EXIST.assertNotNull(user);
        macroTokenDomainService.setUpdateInfo(request, user);
        return new ObjectResponse<>(service.updateUserPwd(user,oldPwd, password) > 0);
    }

    // ***********************************工具****************************************
    /**
     * 获取用户密码，初始化数据库使用，不在程序中使用，故需要进行服务验证
     * @param username 用户名
     * @param password 密码
     * @param md5 是否需要md5加密
     * @return
     */
    @GetMapping("pwd/{username}/{password}")
    @IgnoreUserToken
    public ObjectResponse<String> getPassword(
            @PathVariable("username") String username,
            @PathVariable("password") String password,
            @RequestParam(value = "md5", defaultValue = "false") Boolean md5){

        return new ObjectResponse<String>().setData(
                service.getPassword(username,
                        md5? Md5Utils.getMD5(password,"utf-8") : password));
    }

    /**
     * 获取用户Md5密码，只是对密码md5加密一下
     * @param password 密码
     * @return 返回密码的md5值
     */
    @GetMapping("md5/{password}")
    @IgnoreAuthToken
    public ObjectResponse<String> getMd5(@PathVariable("password") String password ){
        return new ObjectResponse<String>().setData(Md5Utils.getMD5(password,"utf-8"));
    }

    // ***********************************用户信息获取****************************************
    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("front/info")
    @IgnoreClientToken
    public ObjectResponse<FrontUserInfo> getInfo() {

        //TODO 用户菜单树待处理
        JwtUserInfo jwtUserInfo = macroTokenDomainService.getUserInfo();

        FrontUserInfo userInfo = new FrontUserInfo();
        BeanUtil.copyProperties(jwtUserInfo,userInfo);
        return new ObjectResponse<>(userInfo);
    }

    /**
     * TODO 待处理=
     * 获取用户权限
     * @return
     */
    @GetMapping("permission")
    public ListResponse<Map<String, Object>> getUserPermission(){
        JwtUserInfo userInfo = macroTokenDomainService.getUserInfo();
        List<Map<String, Object>> menuByUsername = service.getMenuByUsername(userInfo.getUsername());
        return new ListResponse<Map<String, Object>>().addData(menuByUsername);
    }
    //获取所有权限
    //获取用户菜单
    //获取所有用户菜单
}
