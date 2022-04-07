package cn.wolfcode.car.business.service.impl;

import cn.wolfcode.car.business.domain.BpmnInfo;
import cn.wolfcode.car.business.mapper.BpmnInfoMapper;
import cn.wolfcode.car.business.query.BpmnInfoQueryObject;
import cn.wolfcode.car.business.service.IBpmnInfoService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.config.SystemConfig;
import cn.wolfcode.car.common.exception.BusinessException;
import cn.wolfcode.car.common.util.file.FileUploadUtils;
import cn.wolfcode.car.common.web.AjaxResult;
import com.github.pagehelper.PageHelper;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

@Service
@Transactional
public class BpmnInfoServiceImpl implements IBpmnInfoService {


    @Autowired
    private BpmnInfoMapper bpmnInfoMapper;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Override
    public TablePageInfo<BpmnInfo> query(BpmnInfoQueryObject qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<BpmnInfo> list = bpmnInfoMapper.selectForList(qo);
        return new TablePageInfo<>(list);
    }

    @Override
    public BpmnInfo get(Long id) {
        return bpmnInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(BpmnInfo bpmnInfo) {

        bpmnInfoMapper.insert(bpmnInfo);
    }

    @Override
    public void update(Long id, String info) {
        bpmnInfoMapper.updateByBpmn(id, info);
    }

    @Override
    public void delete(Long id) {

        BpmnInfo bpmnInfo = this.get(id);
        /*
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(bpmnInfo.getActProcessKey())
                .list();

        for (ProcessInstance processInstance : list) {
            if ("bus_car_package".equalsIgnoreCase(bpmnInfo.getBpmnType())) {

            }
        }
        */
        repositoryService.deleteDeployment(bpmnInfo.getDeploymentId(), true);
        File file = new File(SystemConfig.getProfile(), bpmnInfo.getBpmnPath());
        if (file.exists()) {
            file.delete();
        }

        bpmnInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<BpmnInfo> list() {
        return bpmnInfoMapper.selectAll();
    }

    @Override
    public AjaxResult upload(MultipartFile file) {
        if (file != null && file.getSize() > 0) {
            String filename = file.getOriginalFilename();
            String exp = FilenameUtils.getExtension(filename);
            if ("zip".equalsIgnoreCase(exp) || "bpmn".equalsIgnoreCase(exp)) {
                String upload = null;
                try {
                    upload = FileUploadUtils.upload(SystemConfig.getUploadPath(), file);
                    System.err.println(upload);
                } catch (IOException e) {
                    e.printStackTrace();
                    return new AjaxResult().error("流程文件上传失败。");
                }
                return new AjaxResult().success("上传成功", upload);
            } else {
                return new AjaxResult().error("流程定义文件仅支持 bpmn 和 zip 格式。");
            }
        } else {
            return new AjaxResult().error("请勿上传空文件。");
        }
    }


    @Override
    public void deploy(String bpmnPath, String bpmnType, String info) throws FileNotFoundException {
        String lowStr = bpmnPath.toLowerCase();

        FileInputStream inputStream = new FileInputStream(new File(SystemConfig.getProfile(), bpmnPath));
        Deployment deploy = null;

        if (lowStr.endsWith("zip")) {
            deploy = repositoryService.createDeployment()
                    .addZipInputStream(new ZipInputStream(inputStream))
                    .deploy();
        } else if (lowStr.endsWith("bpmn")) {
            deploy = repositoryService.createDeployment()
                    .addInputStream(bpmnPath, inputStream)
                    .deploy();
        }

        if (deploy == null) {
            throw new BusinessException("部署无效");
        }

        BpmnInfo bpmnInfo = new BpmnInfo();
        bpmnInfo.setInfo(info);
        bpmnInfo.setBpmnType(bpmnType);
        bpmnInfo.setDeploymentId(deploy.getId());
        bpmnInfo.setDeployTime(deploy.getDeploymentTime());


        ProcessDefinition singleResult = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploy.getId())
                .latestVersion()
                .singleResult();

        bpmnInfo.setBpmnName(singleResult.getName());
        bpmnInfo.setActProcessId(singleResult.getId());
        bpmnInfo.setActProcessKey(singleResult.getKey());
        bpmnInfo.setBpmnPath(singleResult.getResourceName());
        bpmnInfoMapper.insert(bpmnInfo);
    }

    @Override
    public InputStream getResuroceByType(String deploymentId, String type) {
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .latestVersion()
                .singleResult();

        if ("xml".equalsIgnoreCase(type)) {
            return repositoryService.getResourceAsStream(deploymentId, definition.getResourceName());
        } else if ("png".equalsIgnoreCase(type)) {
            BpmnModel bpmnModel = repositoryService.getBpmnModel(definition.getId());
            DefaultProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();
            InputStream inputStream = diagramGenerator.generateDiagram(bpmnModel, Collections.EMPTY_LIST,
                    Collections.EMPTY_LIST, "宋体", "宋体", "宋体");
            return inputStream;
        }
        return null;
    }

    @Override
    public BpmnInfo queryByType(String type) {
        return bpmnInfoMapper.selectByType(type);
    }
}
