package cn.wolfcode.car.business.web.controller;

import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.service.IUserService;
import cn.wolfcode.car.business.domain.BpmnInfo;
import cn.wolfcode.car.business.domain.ServiceItem;
import cn.wolfcode.car.business.query.ServiceItemQueryObject;
import cn.wolfcode.car.business.service.IBpmnInfoService;
import cn.wolfcode.car.business.service.IServiceItemService;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("business/serviceItem")
public class ServiceItemrController {
    //模板前缀
    private static final String prefix = "business/serviceItem/";

    @Autowired
    private IServiceItemService serviceItemService;

    @Autowired
    private IBpmnInfoService bpmnInfoService;

    @Autowired
    private IUserService userService;

    //页面------------------------------------------------------------
    //列表
    @RequiresPermissions("business:serviceItem:view")
    @RequestMapping("/listPage")
    public String listPage() {
        return prefix + "list";
    }

    @RequiresPermissions("business:serviceItem:add")
    @RequestMapping("/addPage")
    public String addPage() {
        return prefix + "add";
    }

    // @RequiresPermissions("business:serviceItem:auditPage")
    @RequestMapping("/auditPage")
    public String auditPage(Long id,Model model) {
        ServiceItem serviceItem = serviceItemService.get(id);
        model.addAttribute("serviceItem",serviceItem);

        BpmnInfo bpmnInfo = bpmnInfoService.queryByType("car_package");
        model.addAttribute("bpmnInfo",bpmnInfo);

        List<User> directors = userService.queryByRoleKey("shopOwner");
        model.addAttribute("directors",directors);

        List<User> financial = userService.queryByRoleKey("financial");
        model.addAttribute("finances",financial);

        return prefix + "audit";
    }


    @RequiresPermissions("business:serviceItem:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model) {
        model.addAttribute("serviceItem", serviceItemService.get(id));
        return prefix + "edit";
    }

    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("business:serviceItem:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<ServiceItem> query(ServiceItemQueryObject qo) {
        return serviceItemService.query(qo);
    }

    //新增
    @RequiresPermissions("business:serviceItem:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ServiceItem serviceItem) {
        serviceItemService.save(serviceItem);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("business:serviceItem:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(ServiceItem serviceItem) {
        serviceItemService.update(serviceItem);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("business:serviceItem:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        serviceItemService.deleteBatch(ids);
        return AjaxResult.success();
    }

    @RequiresPermissions("business:serviceItem:saleOff")
    @RequestMapping("/saleOff")
    @ResponseBody
    public AjaxResult saleOff(Long id){
        serviceItemService.saleOff(id);
        return AjaxResult.success();
    }

    @RequiresPermissions("business:serviceItem:saleOn")
    @RequestMapping("/saleOn")
    @ResponseBody
    public AjaxResult saleOn(Long id){
        serviceItemService.saleOn(id);
        return AjaxResult.success();
    }


}
