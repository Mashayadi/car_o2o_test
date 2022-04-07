package cn.wolfcode.car.base.web.controller.monitor;

import cn.wolfcode.car.base.domain.Dept;
import cn.wolfcode.car.base.domain.LoginInfo;
import cn.wolfcode.car.base.query.DeptQuery;
import cn.wolfcode.car.base.query.LoginInfoQuery;
import cn.wolfcode.car.base.service.ILoginInfoService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 登录日志控制器
 */
@Controller
@RequestMapping("monitor/loginInfo")
public class LoginInfoController {
    private static final String prefix = "base/monitor/loginInfo/";

    @Autowired
    private ILoginInfoService loginInfoService;




    //页面------------------------------------------------------------
    //列表
    @RequiresPermissions("monitor:logininfor:view")
    @RequestMapping("/listPage")
    public String logininfor() {
        return prefix + "loginInfo";
    }



    //列表
    @RequiresPermissions("monitor:logininfor:list")
    @RequestMapping("/list")
    @ResponseBody
    public TablePageInfo<LoginInfo> list(LoginInfoQuery qo){
        return loginInfoService.query(qo);
    }

    //删除
    @RequiresPermissions("monitor:logininfor:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        loginInfoService.deleteBatch(ids);
        return AjaxResult.success();
    }

    //清除
    @RequiresPermissions("monitor:logininfor:clean")
    @RequestMapping("/clean")
    @ResponseBody
    public AjaxResult clean(){
        loginInfoService.cleanLogininfo();
        return AjaxResult.success();
    }
}
