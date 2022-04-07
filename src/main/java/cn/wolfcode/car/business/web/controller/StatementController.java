package cn.wolfcode.car.business.web.controller;

import cn.wolfcode.car.business.domain.Statement;
import cn.wolfcode.car.business.query.StatementQueryObject;
import cn.wolfcode.car.business.service.IStatementService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/business/statement")
public class StatementController {
    //模板前缀
    private static final String prefix = "/business/statement/";

    @Autowired
    private IStatementService statementService;

    //页面------------------------------------------------------------
    //列表
    @RequiresPermissions("business:statement:view")
    @RequestMapping("/listPage")
    public String listPage() {
        return prefix + "list";
    }

    @RequiresPermissions("business:statement:add")
    @RequestMapping("/addPage")
    public String addPage() {
        return prefix + "add";
    }


    @RequiresPermissions("business:statement:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model) {
        model.addAttribute("statement", statementService.get(id));
        return prefix + "edit";
    }


    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("business:statement:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<Statement> query(StatementQueryObject qo) {
        return statementService.query(qo);
    }

    //新增
    @RequiresPermissions("business:statement:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Statement statement) {
        statementService.save(statement);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("business:statement:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Statement statement) {
        statementService.update(statement);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("business:statement:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        statementService.deleteBatch(ids);
        return AjaxResult.success();
    }

}
