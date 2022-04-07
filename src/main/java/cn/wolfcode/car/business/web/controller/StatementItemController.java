package cn.wolfcode.car.business.web.controller;

import cn.wolfcode.car.base.service.IUserService;
import cn.wolfcode.car.business.domain.ServiceItem;
import cn.wolfcode.car.business.domain.Statement;
import cn.wolfcode.car.business.domain.StatementItem;
import cn.wolfcode.car.business.query.ServiceItemQueryObject;
import cn.wolfcode.car.business.query.StatementItemQueryObject;
import cn.wolfcode.car.business.service.IServiceItemService;
import cn.wolfcode.car.business.service.IStatementItemService;
import cn.wolfcode.car.business.service.IStatementService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/business/statementItem")
public class StatementItemController {
    //模板前缀
    private static final String prefix = "/business/statementItem/";

    @Autowired
    private IStatementService statementService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStatementItemService statementItemService;

    @Autowired
    private IServiceItemService serviceItemService;


    @RequiresPermissions("business:statementItem:view")
    @RequestMapping("/itemDetail")
    public String editDetail(Model model, Long statementId) {
        Statement statement = statementService.get(statementId);
        statement.setPayee(userService.get(statement.getPayeeId()));
        model.addAttribute("statement", statement);

        if (Statement.STATUS_CONSUME.equals(statement.getStatus())) {
            return prefix + "editDetail";
        } else {
            return prefix + "showDetail";
        }
    }

    @RequiresPermissions("business:statementItem:query")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<StatementItem> query(StatementItemQueryObject qo) {
        TablePageInfo<StatementItem> query = statementItemService.query(qo);
        return query;
    }

    @RequiresPermissions("business:statementItem:selectAllSaleOnList")
    @RequestMapping("/selectAllSaleOnList")
    @ResponseBody
    public TablePageInfo<ServiceItem> selectAllSaleOnList(ServiceItemQueryObject qo) {
        qo.setSaleStatus(ServiceItem.SALESTATUS_ON);
        return serviceItemService.query(qo);
    }

    @RequiresPermissions("business:statementItem:saveItems")
    @RequestMapping("/saveItems")
    @ResponseBody
    public AjaxResult saveItems(@RequestBody List<StatementItem> items) {
        statementItemService.saveItem(items);
        return new AjaxResult().success();
    }

    @RequiresPermissions("business:statementItem:payStatement")
    @RequestMapping("/payStatement")
    @ResponseBody
    public AjaxResult payStatement(Long statementId) {
        statementService.payStatement(statementId);
        return new AjaxResult().success();
    }

}
