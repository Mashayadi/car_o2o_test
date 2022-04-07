package cn.wolfcode.car.business.web.controller;

import cn.wolfcode.car.business.domain.CarPackageAudit;
import cn.wolfcode.car.business.query.CarPackageAuditQueryObject;
import cn.wolfcode.car.business.service.ICarPackageAuditService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("business/carPackageAudit")
public class CarPackageAuditController {
    //模板前缀
    private static final String prefix = "business/carPackageAudit/";

    @Autowired
    private ICarPackageAuditService carPackageAuditService;

    //页面------------------------------------------------------------
    //列表
    @RequiresPermissions("business:carPackageAudit:view")
    @RequestMapping("/listPage")
    public String listPage() {
        return prefix + "list";
    }

    @RequiresPermissions("business:carPackageAudit:add")
    @RequestMapping("/addPage")
    public String addPage() {
        return prefix + "add";
    }


    @RequiresPermissions("business:carPackageAudit:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model) {
        model.addAttribute("carPackageAudit", carPackageAuditService.get(id));
        return prefix + "edit";
    }

    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("business:carPackageAudit:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<CarPackageAudit> query(CarPackageAuditQueryObject qo) {
        return carPackageAuditService.query(qo);
    }

    //新增
    @RequiresPermissions("business:carPackageAudit:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CarPackageAudit carPackageAudit) {
        carPackageAuditService.save(carPackageAudit);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("business:carPackageAudit:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(CarPackageAudit carPackageAudit) {
        carPackageAuditService.update(carPackageAudit);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("business:carPackageAudit:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        carPackageAuditService.deleteBatch(ids);
        return AjaxResult.success();
    }

}
