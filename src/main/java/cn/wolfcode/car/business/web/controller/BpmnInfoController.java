package cn.wolfcode.car.business.web.controller;

import cn.wolfcode.car.business.domain.BpmnInfo;
import cn.wolfcode.car.business.query.BpmnInfoQueryObject;
import cn.wolfcode.car.business.service.IBpmnInfoService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.config.SystemConfig;
import cn.wolfcode.car.common.util.file.FileUploadUtils;
import cn.wolfcode.car.common.web.AjaxResult;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("business/bpmnInfo")
public class BpmnInfoController {
    //模板前缀
    private static final String prefix = "business/bpmnInfo/";

    @Autowired
    private IBpmnInfoService bpmnInfoService;

    //页面------------------------------------------------------------
    //列表
    @RequiresPermissions("business:bpmnInfo:view")
    @RequestMapping("/listPage")
    public String listPage() {
        return prefix + "list";
    }

    @RequiresPermissions("business:bpmnInfo:add")
    @RequestMapping("/addPage")
    public String addPage() {
        return prefix + "add";
    }


    @RequiresPermissions("business:bpmnInfo:edit")
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model) {
        model.addAttribute("bpmnInfo", bpmnInfoService.get(id));
        return prefix + "edit";
    }

    @RequiresPermissions("business:bpmnInfo:deployPage")
    @RequestMapping("/deployPage")
    public String deployPage() {
        return prefix + "deploy";
    }


    //数据-----------------------------------------------------------
    //列表
    @RequiresPermissions("business:bpmnInfo:list")
    @RequestMapping("/query")
    @ResponseBody
    public TablePageInfo<BpmnInfo> query(BpmnInfoQueryObject qo) {
        return bpmnInfoService.query(qo);
    }

    //新增
    @RequiresPermissions("business:bpmnInfo:add")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BpmnInfo bpmnInfo) {
        bpmnInfoService.save(bpmnInfo);
        return AjaxResult.success();
    }

    //编辑
    @RequiresPermissions("business:bpmnInfo:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Long id,String info) {
        bpmnInfoService.update(id,info);
        return AjaxResult.success();
    }


    //删除
    @RequiresPermissions("business:bpmnInfo:delete")
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        bpmnInfoService.delete(id);
        return AjaxResult.success();
    }


    @RequiresPermissions("business:bpmnInfo:upload")
    @RequestMapping("/upload")
    @ResponseBody
    public AjaxResult upload(MultipartFile file) {
        return bpmnInfoService.upload(file);
    }


    @RequiresPermissions("business:bpmnInfo:deploy")
    @RequestMapping("/deploy")
    @ResponseBody
    public AjaxResult deploy(String bpmnPath, String bpmnType, String info) throws FileNotFoundException {
        bpmnInfoService.deploy(bpmnPath, bpmnType, info);
        return AjaxResult.success();
    }

    @RequiresPermissions("business:bpmnInfo:readResource")
    @RequestMapping("/readResource")
    @ResponseBody
    public void readResource(String deploymentId, String type, HttpServletResponse response) throws IOException {
         InputStream inputStream = bpmnInfoService.getResuroceByType(deploymentId,type);
        IOUtils.copy(inputStream,response.getOutputStream());
    }

}
