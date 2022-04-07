package cn.wolfcode.car.frontend;


import cn.wolfcode.car.common.web.AjaxResult;
import cn.wolfcode.car.shiro.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 前端项目请求处理
 */
@Controller
@RequestMapping("frontend")
public class FrontendController {



    //当前登录用户
    @RequestMapping("getCurrentUser")
    @ResponseBody
    public AjaxResult getCurrentUser(){
        return AjaxResult.success(ShiroUtils.getUser());
    }











}
