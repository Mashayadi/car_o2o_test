package cn.wolfcode.car.base.web.controller.system;

import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.service.IUserService;
import cn.wolfcode.car.common.async.AsyncFactory;
import cn.wolfcode.car.common.async.AsyncManager;
import cn.wolfcode.car.common.constant.Constants;
import cn.wolfcode.car.common.util.MessageUtils;
import cn.wolfcode.car.common.web.AjaxResult;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 登录控制器
 */
@Controller
public class LoginController {

    @Autowired
    private IUserService userService;


    //登录页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //用户登录
    @RequestMapping("/userLogin")
    @ResponseBody
    public AjaxResult userLogin(String username, String password){
        User user = userService.queryByUserName(username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, userService.encryptPassword(username, password, user.getSalt()));
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
            return AjaxResult.success();
        }catch (AuthenticationException e){
            String msg = "用户或密码错误";
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            return AjaxResult.error(msg);
        }
    }


}
