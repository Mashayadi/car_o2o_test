package cn.wolfcode.car.base.web.controller.system;


import cn.wolfcode.car.base.domain.Config;
import cn.wolfcode.car.base.query.ConfigQuery;
import cn.wolfcode.car.base.service.IConfigService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统参数控制器
 */
@Controller
@RequestMapping("system/config")
public class ConfigController {
    //模板前缀
    private static final String prefix = "base/system/config/";

    @Autowired
    private IConfigService configService;


    //页面------------------------------------------------------------
    //列表
    @RequiresPermissions("system:config:view")
    @RequestMapping("/listPage")
    public String listPage(){
        return prefix + "list";
    }


    @RequiresPermissions("system:config:add")
    @RequestMapping("/addPage")
    public String addPage(){
        return prefix + "add";
    }

    @RequiresPermissions("system:config:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        model.addAttribute("config", configService.get(id));
        return prefix + "edit";
    }

    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("system:config:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<Config> query(ConfigQuery qo){
        return configService.query(qo);
    }

    //判断是否唯一
    @RequestMapping("/checkConfigKeyUnique")
    @ResponseBody
    public String  checkConfigKeyUnique(String key){
        boolean ret = configService.checkKeyExsit(key);
        return ret?"1":"0";
    }

    //新增
    @RequiresPermissions("system:config:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Config config){
        configService.save(config);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("system:config:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Config config){
        configService.update(config);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("system:config:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        configService.deleteBatch(ids);
        return AjaxResult.success();
    }
}
