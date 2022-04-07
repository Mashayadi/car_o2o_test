package cn.wolfcode.car.base.web.controller.system;


import cn.wolfcode.car.base.domain.Dept;
import cn.wolfcode.car.base.query.DeptQuery;
import cn.wolfcode.car.base.service.IDeptService;
import cn.wolfcode.car.common.base.domain.Ztree;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 部门控制器
 */
@Controller
@RequestMapping("system/dept")
public class DeptController {
    //模板前缀
    private static final String prefix = "base/system/dept/";

    @Autowired
    private IDeptService deptService;


    //页面------------------------------------------------------------

    //列表
    @RequiresPermissions("system:dept:view")
    @RequestMapping("/listPage")
    public String listPage(){
        return prefix + "list";
    }

    //添加页面
    @RequiresPermissions("system:dept:add")
    @RequestMapping("/addPage")
    public String addPage(Long parentId, Model model){
        model.addAttribute("dept", deptService.get(parentId));
        model.addAttribute("parentId", parentId);
        return prefix + "add";
    }

    //tree列表页面
    @RequestMapping(value = "/deptTreePage")
    public String selectDeptTree(Long deptId,Model model){
        model.addAttribute("dept",deptService.get(deptId));
        model.addAttribute("deptId",deptId);
        return prefix + "/tree";
    }


    //编辑页面
    @RequiresPermissions("system:dept:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){

        Dept dept = deptService.get(id);
        Dept parent = deptService.get(dept.getParentId());
        if(parent != null){
            dept.setParentName(parent.getName());
        }

        model.addAttribute("dept", dept);
        return prefix + "edit";
    }

    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("system:dept:list")
    @RequestMapping("/list")
    @ResponseBody
    public List<Dept> list(DeptQuery qo){
        return deptService.list(qo);
    }

    //新增
    @RequiresPermissions("system:dept:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Dept dept){
        deptService.save(dept);
        return AjaxResult.success();
    }


    //tree数据
    @RequestMapping(value = "/treeData")
    @ResponseBody
    public List<Ztree>  treeData(DeptQuery qo){
        return  deptService.selectDeptTree(qo);
    }

    //编辑
    @RequiresPermissions("system:dept:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Dept dept){
        deptService.update(dept);
        return AjaxResult.success();
    }




    //判断是否唯一
    @RequestMapping("/checkDeptNameUnique")
    @ResponseBody
    public String  checkNameUnique(String name){
        boolean ret = deptService.checkNameExsit(name);
        return ret?"1":"0";
    }

    //删除
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long id){
        deptService.delete(id);
        return AjaxResult.success();
    }


    //加载角色部门（数据权限）列表树
    @RequestMapping("/roleDeptTreeData")
    @ResponseBody
    public List<Ztree> deptTreeData(Long roleId)
    {
        List<Ztree> ztrees = deptService.roleDeptTreeData(roleId);
        return ztrees;
    }

}
