package cn.wolfcode.car.base.web.controller.monitor;

import cn.wolfcode.car.base.domain.server.ServerInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务器监控控制器
 */
@Controller
@RequestMapping("monitor/server")
public class ServerController {
    private static final String prefix = "base/monitor/server/";

    @RequiresPermissions("monitor:server:view")
    @RequestMapping()
    public String server(Model model) throws Exception{
        ServerInfo server = new ServerInfo();
        server.init();
        model.addAttribute("server", server);
        return prefix + "/server";
    }
}
