package cn.wolfcode.car.base.web.controller.system;


import cn.wolfcode.car.base.domain.DictData;
import cn.wolfcode.car.base.domain.DictType;
import cn.wolfcode.car.base.query.DictDataQuery;
import cn.wolfcode.car.base.query.DictTypeQuery;
import cn.wolfcode.car.base.service.IDictDataService;
import cn.wolfcode.car.base.service.IDictTypeService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典数据控制器
 */
@Controller
@RequestMapping("system/dictData")
public class DictDataController {
    //模板前缀
    private static final String prefix = "base/system/dictData/";

    @Autowired
    private IDictDataService dictDataService;

    @Autowired
    private IDictTypeService dictTypeService;

    //页面------------------------------------------------------------
    //列表
    @RequiresPermissions("system:dict:view")
    @RequestMapping("/listPage")
    public String listPage(Long typeId, Model model){
        //dictList
        model.addAttribute("dictList", dictTypeService.list());
        model.addAttribute("dict", dictTypeService.get(typeId));
        return prefix + "list";
    }
    //添加页面
    @RequiresPermissions("system:dict:add")
    @RequestMapping("/addPage")
    public String addPage(String type, Model model){
        model.addAttribute("type", type);
        return prefix + "add";
    }

    //编辑页面
    @RequiresPermissions("system:dict:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        model.addAttribute("dict", dictDataService.get(id));
        return prefix + "edit";
    }
    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("system:dict:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<DictData> query(DictDataQuery qo){
        return dictDataService.query(qo);
    }

    //添加
    @RequiresPermissions("system:dict:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult add(DictData data){
        dictDataService.save(data);
        return AjaxResult.success();
    }
    //编辑
    @RequiresPermissions("system:dict:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(DictData data){
        dictDataService.update(data);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("system:dict:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        dictDataService.deleteBatch(ids);
        return AjaxResult.success();
    }

}
