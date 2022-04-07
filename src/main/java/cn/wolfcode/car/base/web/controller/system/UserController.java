package cn.wolfcode.car.base.web.controller.system;

import cn.wolfcode.car.base.domain.Role;
import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.query.UserQuery;
import cn.wolfcode.car.base.service.IDeptService;
import cn.wolfcode.car.base.service.IPostService;
import cn.wolfcode.car.base.service.IRoleService;
import cn.wolfcode.car.base.service.IUserService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import cn.wolfcode.car.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户控制器
 */
@Controller
@RequestMapping("system/user")
public class UserController {

    //模板前缀
    private static final String prefix = "base/system/user/";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;
    @Autowired
    private IDeptService deptService;

    //页面------------------------------------------------------------
    //列表
    @RequiresPermissions("system:user:view")
    @RequestMapping("/listPage")
    public String listPage(){
        return prefix + "list";
    }

    //新增
    @RequiresPermissions("system:user:add")
    @RequestMapping("/addPage")
    public String addPage(Model model) {
        model.addAttribute("roles", roleService.list());
        model.addAttribute("posts", postService.list());
        return prefix + "add";
    }

    //修改
    @RequiresPermissions("system:user:edit")
    @RequestMapping("/editPage")
    public String edit(Long id, Model model){
        User user = userService.get(id);
        user.setDept(deptService.get(user.getDeptId()));
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.listAllWithUserId(id));
        model.addAttribute("posts", postService.listAllWithUserId(id));
        return prefix + "edit";
    }

    //重置密码
    @RequiresPermissions("system:user:resetPwd")
    @RequestMapping("/resetPwdPage")
    public String resetPwd( Long userId,Model model){
        model.addAttribute("user", userService.get(userId));
        return prefix + "/resetPwd";
    }

    //进入授权角色页
    @RequiresPermissions("system:user:add")
    @RequestMapping("/authRolePage")
    public String authRole(Long userId, Model model){
        User user = userService.get(userId);
        // 获取用户所属的角色列表
        List<Role> roles = roleService.listAllWithUserId(userId);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return prefix + "/authRole";
    }


    //数据-----------------------------------------------------------
    @RequiresPermissions("system:user:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<User> query(UserQuery qo) {
        return userService.query(qo);
    }


    //用户状态修改
    @RequiresPermissions("system:user:edit")
    @RequestMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Long userId, char status){
        userService.changeStatus(userId, status);
        return AjaxResult.success();
    }

    //校验手机号码
    @RequestMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(String loginName, Long userId){
        return userService.checkLoginName(loginName,userId)?"1":"0";
    }

    //校验手机号码
    @RequestMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(String phonenumber, Long userId){
        return userService.checkPhone(phonenumber,userId)?"1":"0";
    }

    //校验email邮箱
    @RequestMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(String email, Long userId){
        return userService.checkEmail(email,userId)?"1":"0";
    }


    //新增保存用户
    @RequiresPermissions("system:user:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(User user){

        userService.save(user);

        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("system:user:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(User user){
        userService.update(user);
        return AjaxResult.success();
    }

    //重置密码
    @RequiresPermissions("system:user:edit")
    @RequestMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(Long userId,String password ){
        userService.resetUserPwd(userId, password);
        return AjaxResult.success();
    }


    //用户授权角色
    @RequiresPermissions("system:user:edit")
    @RequestMapping("/authRole/insertAuthRole")
    @ResponseBody
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds){
        userService.insertUserAuth(userId, roleIds);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("system:user:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        userService.deleteUserByIds(ids);
        return AjaxResult.success();
    }

}
