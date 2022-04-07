package cn.wolfcode.car.base.web.controller.system;


import cn.wolfcode.car.base.domain.Post;
import cn.wolfcode.car.base.domain.Post;
import cn.wolfcode.car.base.query.PostQuery;
import cn.wolfcode.car.base.query.PostQuery;
import cn.wolfcode.car.base.service.IPostService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 岗位控制器
 */
@Controller
@RequestMapping("system/post")
public class PostController {
    //模板前缀
    private static final String prefix = "base/system/post/";

    @Autowired
    private IPostService postService;

    //页面------------------------------------------------------------
    //列表
    @RequiresPermissions("system:post:view")
    @RequestMapping("/listPage")
    public String listPage(){
        return prefix + "list";
    }

    @RequiresPermissions("system:post:add")
    @RequestMapping("/addPage")
    public String addPage(){
        return prefix + "add";
    }


    @RequiresPermissions("system:post:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        model.addAttribute("post", postService.get(id));
        return prefix + "edit";
    }

    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("system:post:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<Post> query(PostQuery qo){
        return postService.query(qo);
    }


    //判断name是否唯一
    @RequestMapping("/checkPostNameUnique")
    @ResponseBody
    public String  checkPostNameUnique(String name){
        boolean ret = postService.checkNameExsit(name);
        return ret?"1":"0";
    }

    //判断code是否唯一
    @RequestMapping("/checkPostCodeUnique")
    @ResponseBody
    public String  checkPostCodeUnique(String code){
        boolean ret = postService.checkCodeExsit(code);
        return ret?"1":"0";
    }


    //新增
    @RequiresPermissions("system:post:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Post post){
        postService.save(post);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("system:post:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Post post){
        postService.update(post);
        return AjaxResult.success();
    }

    //删除
    @RequiresPermissions("system:post:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        postService.deleteBatch(ids);
        return AjaxResult.success();
    }

}
