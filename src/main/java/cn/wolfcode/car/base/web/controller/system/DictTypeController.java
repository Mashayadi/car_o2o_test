package cn.wolfcode.car.base.web.controller.system;


import cn.wolfcode.car.base.domain.DictType;
import cn.wolfcode.car.base.query.DictTypeQuery;
import cn.wolfcode.car.base.service.IDictTypeService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 字典类型控制器
 */
@Controller
@RequestMapping("system/dictType")
public class DictTypeController {
    //模板前缀
    private static final String prefix = "base/system/dictType/";

    @Autowired
    private IDictTypeService dictTypeService;


    //页面------------------------------------------------------------

    //列表页面
    @RequiresPermissions("system:dict:view")
    @RequestMapping("/listPage")
    public String listPage(){
        return prefix + "list";
    }


    @RequiresPermissions("system:dict:add")
    @RequestMapping("/addPage")
    public String addPage(){
        return prefix + "add";
    }

    @RequiresPermissions("system:dict:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        model.addAttribute("dict", dictTypeService.get(id));
        return prefix + "edit";
    }

    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("system:dict:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<DictType> query(DictTypeQuery qo){
        return dictTypeService.query(qo);
    }

    //判断type是否唯一
    @RequestMapping("/checkDictTypeUnique")
    @ResponseBody
    public String  checkTypeUnique(String type){
        boolean ret = dictTypeService.checkTypeExsit(type);
        return ret?"1":"0";
    }


    //新增
    @RequiresPermissions("system:dict:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DictType dictType){
        dictTypeService.save(dictType);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("system:dict:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(DictType dictType){
        dictTypeService.update(dictType);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("system:dict:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        dictTypeService.deleteBatch(ids);
        return AjaxResult.success();
    }
}
