package cn.wolfcode.car.business.service;

import cn.wolfcode.car.business.domain.BpmnInfo;
import cn.wolfcode.car.business.query.BpmnInfoQueryObject;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.web.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public interface IBpmnInfoService {

    TablePageInfo<BpmnInfo> query(BpmnInfoQueryObject qo);

    BpmnInfo get(Long id);

    void save(BpmnInfo bpmnInfo);

    void update(Long id,String info );

    void delete(Long id);

    List<BpmnInfo> list();

    AjaxResult upload(MultipartFile file);

    void deploy(String bpmnPath, String bpmnType, String info) throws FileNotFoundException;

    InputStream getResuroceByType(String deploymentId, String type);

    BpmnInfo queryByType(String type);
}
