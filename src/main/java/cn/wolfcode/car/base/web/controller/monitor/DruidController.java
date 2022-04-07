package cn.wolfcode.car.base.web.controller.monitor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * druid 监控 控制器
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController {
    private String prefix = "redirect:/druid";

    @RequiresPermissions("monitor:data:view")
    @RequestMapping()
    public String index() {
        return prefix + "/index.html";
    }
}
