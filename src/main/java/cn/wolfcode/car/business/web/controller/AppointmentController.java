package cn.wolfcode.car.business.web.controller;

import cn.wolfcode.car.business.domain.Appointment;
import cn.wolfcode.car.business.query.AppointmentQueryObject;
import cn.wolfcode.car.business.service.IAppointmentService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("business/appointment")
public class AppointmentController {
    //模板前缀
    private static final String prefix = "business/appointment/";

    @Autowired
    private IAppointmentService appointmentService;

    //页面------------------------------------------------------------
    //列表
    @RequiresPermissions("business:appointment:view")
    @RequestMapping("/listPage")
    public String listPage() {
        return prefix + "list";
    }

    @RequiresPermissions("business:appointment:add")
    @RequestMapping("/addPage")
    public String addPage() {
        return prefix + "add";
    }


    @RequiresPermissions("business:appointment:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model) {
        model.addAttribute("appointment", appointmentService.get(id));
        return prefix + "edit";
    }

    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("business:appointment:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<Appointment> query(AppointmentQueryObject qo) {
        return appointmentService.query(qo);
    }

    //新增
    @RequiresPermissions("business:appointment:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Appointment appointment) {
        appointmentService.save(appointment);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("business:appointment:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Appointment appointment) {
        appointmentService.update(appointment);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("business:appointment:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        appointmentService.deleteBatch(ids);
        return AjaxResult.success();
    }

    // 客户已到店
    @RequiresPermissions("business:appointment:arrival")
    @RequestMapping("/arrival")
    @ResponseBody
    public AjaxResult arrival(Long id) {
        appointmentService.updateArrival(id);
        return AjaxResult.success();
    }

    // 客户取消预约
    @RequiresPermissions("business:appointment:cancel")
    @RequestMapping("/cancel")
    @ResponseBody
    public AjaxResult cancel(Long id) {
        appointmentService.updateCancel(id);
        return AjaxResult.success();
    }

}
