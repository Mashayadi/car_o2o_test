package cn.wolfcode.car.base.web.controller.system;


import cn.wolfcode.car.base.domain.Notice;
import cn.wolfcode.car.base.query.NoticeQuery;
import cn.wolfcode.car.base.service.INoticeService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通告控制器
 */
@Controller
@RequestMapping("system/notice")
public class NoticeController {
    //模板前缀
    private static final String prefix = "base/system/notice/";

    @Autowired
    private INoticeService noticeService;


    //页面------------------------------------------------------------

    //列表
    @RequiresPermissions("system:notice:view")
    @RequestMapping("/listPage")
    public String listPage(){
        return prefix + "list";
    }

    @RequiresPermissions("system:notice:add")
    @RequestMapping("/addPage")
    public String addPage(){
        return prefix + "add";
    }

    @RequiresPermissions("system:notice:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        model.addAttribute("notice", noticeService.get(id));
        return prefix + "edit";
    }

    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("system:notice:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<Notice> query(NoticeQuery qo){
        return noticeService.query(qo);
    }



    //新增
    @RequiresPermissions("system:notice:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Notice notice){
        noticeService.save(notice);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("system:notice:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Notice notice){
        noticeService.update(notice);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("system:notice:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        noticeService.deleteBatch(ids);
        return AjaxResult.success();
    }
}
