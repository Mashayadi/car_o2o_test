package cn.wolfcode.car.base.web.controller.system;


import cn.wolfcode.car.base.domain.Post;
import cn.wolfcode.car.base.domain.Role;
import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.service.IDeptService;
import cn.wolfcode.car.base.service.IPostService;
import cn.wolfcode.car.base.service.IRoleService;
import cn.wolfcode.car.base.service.IUserService;
import cn.wolfcode.car.common.config.SystemConfig;
import cn.wolfcode.car.common.util.file.FileUploadUtils;
import cn.wolfcode.car.common.web.AjaxResult;
import cn.wolfcode.car.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 个人信息 控制器
 */
@Controller
@RequestMapping("system/user/profile")
public class ProfileController {
    private String prefix = "base/system/user/profile";
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IDeptService deptService;


    //个人信息
    @RequestMapping("/profilePage")
    public String profile(Model model){
        User user = ShiroUtils.getUser();
        user.setDept(deptService.get(user.getDeptId()));
        model.addAttribute("user", user);

        List<Role> roles = roleService.queryByUserId(user.getId());
        StringBuffer idsStr = new StringBuffer();
        for (Role role : roles){
            idsStr.append(role.getName()).append(",");
        }
        model.addAttribute("roleGroup", idsStr.substring(0, idsStr.length() - 1));
        List<Post> posts = postService.queryByUserId(user.getId());
        idsStr = new StringBuffer();
        for (Post post : posts){
            idsStr.append(post.getName()).append(",");
        }
        model.addAttribute("postGroup",idsStr.substring(0, idsStr.length() - 1));
        return prefix + "/profile";
    }

    //修改密码
    @RequestMapping("/resetPwdPage")
    public String resetPwd(Model model){
        User user = ShiroUtils.getUser();
        model.addAttribute("user", userService.get(user.getId()));
        return prefix + "/resetPwd";
    }

    //修改密码
    @RequestMapping("/avatarPage")
    public String avatarPage(Model model){
        User user = ShiroUtils.getUser();
        model.addAttribute("user", userService.get(user.getId()));
        return prefix + "/avatar";
    }
    ///---------------------------------------------------------

    //修改用户
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(User user){
        User currentUser = ShiroUtils.getUser();
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhonenumber(user.getPhonenumber());
        currentUser.setSex(user.getSex());

        userService.updateInfo(currentUser);
        ShiroUtils.setUser(userService.get(currentUser.getId()));
        return AjaxResult.success();
    }


    @RequestMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password){
        User user = ShiroUtils.getUser();
        return userService.matches(user, password);
    }

    @RequestMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(String oldPassword, String newPassword){
        userService.resetUserPwd(oldPassword, newPassword);
        return  AjaxResult.success();
    }



    //保存头像
    @RequestMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(MultipartFile avatarfile) throws IOException {
        User currentUser = ShiroUtils.getUser();
        if (!avatarfile.isEmpty()) {
            String avatar = FileUploadUtils.upload(SystemConfig.getAvatarPath(), avatarfile);
            userService.updateAvatar(currentUser.getId(), avatar);
            ShiroUtils.setUser(userService.get(currentUser.getId()));
        }
        return AjaxResult.success();
    }

}
