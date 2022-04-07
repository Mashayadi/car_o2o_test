package cn.wolfcode.car.base.web.controller.system;


import cn.wolfcode.car.base.domain.Menu;
import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.service.IConfigService;
import cn.wolfcode.car.base.service.IMenuService;
import cn.wolfcode.car.base.service.IUserService;
import cn.wolfcode.car.common.config.SystemConfig;
import cn.wolfcode.car.common.util.CookieUtils;
import cn.wolfcode.car.common.util.StringUtils;
import cn.wolfcode.car.common.web.AjaxResult;
import cn.wolfcode.car.common.web.ServletUtils;
import cn.wolfcode.car.shiro.ShiroUtils;
import cn.wolfcode.car.shiro.constant.ShiroConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统首页控制器
 */
@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IConfigService configService;

    @RequestMapping("/index")
    public String index(Model model){
        //根据用户查询菜单
        User user = ShiroUtils.getUser();
        List<Menu> menus = menuService.queryMenuTreeUser(user);
        model.addAttribute("menus", menus);
        model.addAttribute("user", user);


        model.addAttribute("sideTheme", configService.getValue("sys.index.sideTheme"));
        model.addAttribute("skinName", configService.getValue("sys.index.skinName"));
        model.addAttribute("ignoreFooter", configService.getValue("sys.index.ignoreFooter"));
        model.addAttribute("copyrightYear", "2021");
        model.addAttribute("demoEnabled", false);
        model.addAttribute("isDefaultModifyPwd", false);
        model.addAttribute("isPasswordExpired", false);


        // 菜单导航显示风格
        String menuStyle =  configService.getValue("sys.index.menuStyle");;
        // 移动端，默认使左侧导航菜单，否则取默认配置
        String indexStyle = ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")) ? "index" : menuStyle;
        // 优先Cookie配置导航菜单
        Cookie[] cookies = ServletUtils.getRequest().getCookies();

        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (StringUtils.isNotEmpty(cookie.getName()) && "nav-style".equalsIgnoreCase(cookie.getName())){
                    indexStyle = cookie.getValue();
                    break;
                }
            }
        }
        String webIndex = "topnav".equalsIgnoreCase(indexStyle) ? "index-topnav" : "index";
        return webIndex;
    }

    // 锁定屏幕
    @RequestMapping("/lockscreen")
    public String lockscreen(Model model) {
        model.addAttribute("user", ShiroUtils.getUser());
        ServletUtils.getSession().setAttribute(ShiroConstants.LOCK_SCREEN, true);
        return "lock";
    }

    // 切换主题
    @GetMapping("/switchSkin")
    public String switchSkin(){
        return "skin";
    }



    // 解锁屏幕
    @RequestMapping("/unlockscreen")
    @ResponseBody
    public AjaxResult unlockscreen(String password) {
        User user = ShiroUtils.getUser();
        if (StringUtils.isNull(user)) {
            return AjaxResult.error("服务器超时，请重新登陆");
        }
        if (userService.encryptPassword(user.getLoginName(),password, user.getSalt()).equals(user.getPassword())) {
            ServletUtils.getSession().removeAttribute(ShiroConstants.LOCK_SCREEN);
            return AjaxResult.success();
        }
        return AjaxResult.error("密码不正确，请重新输入。");
    }

    // 系统介绍
    @RequestMapping("/main")
    public String main(Model model){
        model.addAttribute("version", SystemConfig.getVersion());
        return "main";
    }


    // 切换菜单
    @RequestMapping("/menuStyle")
    public void menuStyle(String style, HttpServletResponse response){
        CookieUtils.setCookie(response, "nav-style", style);
    }



}
