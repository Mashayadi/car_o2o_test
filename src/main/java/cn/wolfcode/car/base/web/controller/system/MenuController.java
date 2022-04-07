package cn.wolfcode.car.base.web.controller.system;


import cn.wolfcode.car.base.domain.Menu;
import cn.wolfcode.car.base.query.MenuQuery;
import cn.wolfcode.car.base.service.IMenuService;
import cn.wolfcode.car.common.base.domain.Ztree;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单控制器
 */
@Controller
@RequestMapping("system/menu")
public class MenuController {
    //模板前缀
    private static final String prefix = "base/system/menu/";

    @Autowired
    private IMenuService menuService;

    //页面------------------------------------------------------------

    //列表
    @RequiresPermissions("system:menu:view")
    @RequestMapping("/listPage")
    public String listPage(){
        return prefix + "list";
    }

    @RequiresPermissions("system:menu:add")
    @RequestMapping("/addPage")
    public String addPage(Long parentId, Model model){
        Menu menu = menuService.get(parentId);
        if(menu == null){
            menu = new Menu();
            menu.setId(0L);
            menu.setName("主目录");
        }
        model.addAttribute("menu", menu);
        return prefix + "add";
    }

    @RequiresPermissions("system:menu:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        model.addAttribute("menu", menuService.get(id));
        return prefix + "edit";
    }

    @RequestMapping("/menuTreePage")
    public String menuTreePage(Long id, Model model){
        model.addAttribute("menu", menuService.get(id));
        return prefix + "tree";
    }


    //数据-----------------------------------------------------------

    //列表
    @RequiresPermissions("system:menu:list")
    @RequestMapping("/list")
    @ResponseBody
    public List<Menu> query(MenuQuery qo){
        return menuService.list(qo);
    }


    //新增
    @RequiresPermissions("system:menu:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Menu menu){
        menuService.save(menu);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("system:menu:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Menu menu){
        menuService.update(menu);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("system:menu:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long id){
        menuService.delete(id);
        return AjaxResult.success();
    }
    @RequestMapping("/menuTreeData")
    @ResponseBody
    public  List<Ztree> menuTreeData(MenuQuery qo){
        //加上用户id
        return menuService.queryMenuTreeData(qo);
    }
    //校验菜单名称
    @RequestMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(String name, Long parentId){
        boolean ret = menuService.checkNameUnique(parentId, name);
        return ret?"1":"0";
    }


}
