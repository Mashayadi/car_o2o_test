package cn.wolfcode.car.base.web.controller.system;


import cn.wolfcode.car.base.domain.Role;
import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.query.RoleQuery;
import cn.wolfcode.car.base.query.UserQuery;
import cn.wolfcode.car.base.service.IRoleService;
import cn.wolfcode.car.base.service.IUserService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import cn.wolfcode.car.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 角色控制器
 */
@Controller
@RequestMapping("system/role")
public class RoleController {
    //模板前缀
    private static final String prefix = "base/system/role/";

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;


    //页面------------------------------------------------------------

    //列表
    @RequiresPermissions("system:role:view")
    @RequestMapping("/listPage")
    public String listPage(){
        return prefix + "list";
    }

    @RequiresPermissions("system:role:add")
    @RequestMapping("/addPage")
    public String addPage(){
        return prefix + "add";
    }

    @RequiresPermissions("system:role:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        model.addAttribute("role", roleService.get(id));
        return prefix + "edit";
    }

    //数据权限页面
    @RequiresPermissions("system:role:edit")
    @RequestMapping("/authDataScopePage")
    public String authDataScopePage(Long roleId, Model model){
        model.addAttribute("role", roleService.get(roleId));
        return prefix + "dataScope";
    }
    //用户权限页面
    @RequiresPermissions("system:role:edit")
    @RequestMapping("/authUserPage")
    public String authUserPage(Long roleId, Model model){
        model.addAttribute("role", roleService.get(roleId));
        return prefix + "authUser";
    }

    //用户选择页面
    @RequiresPermissions("system:role:edit")
    @RequestMapping("/authUser/selectUserPage")
    public String selectUserPage(Long roleId, Model model){
        model.addAttribute("role", roleService.get(roleId));
        return prefix +"/selectUser";
    }


    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("system:role:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<Role> query(RoleQuery qo){
        return roleService.query(qo);
    }

    //判断是否唯一
    @RequestMapping("/checkRoleNameUnique")
    @ResponseBody
    public String  checkRoleNameUnique(String name){
        boolean ret = roleService.checkNameExsit(name);
        return ret?"1":"0";
    }
    //判断是否唯一
    @RequestMapping("/checkRoleKeyUnique")
    @ResponseBody
    public String  checkRoleKeyUnique(String rkey){
        boolean ret = roleService.checkKeyExsit(rkey);
        return ret?"1":"0";
    }

    //新增
    @RequiresPermissions("system:role:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Role role){
        roleService.save(role);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("system:role:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Role role){
        roleService.update(role);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("system:role:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        roleService.deleteBatch(ids);
        return AjaxResult.success();
    }

    //角色状态修改
    @RequiresPermissions("system:role:edit")
    @RequestMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Long roleId, String status) {
        roleService.changeStatus(roleId, status);
        return AjaxResult.success();
    }

    //保存角色分配数据权限
    @RequiresPermissions("system:role:edit")
    @RequestMapping("/authDataScope")
    @ResponseBody
    public AjaxResult authDataScopeSave(Role role) {
        roleService.authDataScope(role);
        return AjaxResult.success();
    }


    //查询已分配用户角色列表
    @RequestMapping("/authUser/allocatedList")
    @ResponseBody
    public TablePageInfo<User> allocatedList(UserQuery qo){
        return userService.queryAllocated(qo);
    }

    //查询未分配用户角色列表
    @RequestMapping("/authUser/unallocatedList")
    @ResponseBody
    public TablePageInfo<User> unallocatedList(UserQuery qo){
        return userService.queryUnAllocated(qo);
    }

    //批量选择用户授权
    @RequestMapping("/authUser/selectAll")
    @ResponseBody
    public AjaxResult selectAuthUserAll(Long roleId, String userIds) {
        roleService.insertAuthUsers(roleId, userIds);
        return AjaxResult.success();
    }

    //取消授权
    @RequestMapping("/authUser/cancel")
    @ResponseBody
    public AjaxResult cancelAuthUser(Long roleId, String userIds){
        roleService.deleteAuthUser(roleId, userIds);
        return AjaxResult.success();
    }

}
