package cn.wolfcode.car.business.service.impl;

import cn.wolfcode.car.business.domain.CarPackageAudit;
import cn.wolfcode.car.business.mapper.CarPackageAuditMapper;
import cn.wolfcode.car.business.query.CarPackageAuditQueryObject;
import cn.wolfcode.car.business.service.ICarPackageAuditService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CarPackageAuditServiceImpl implements ICarPackageAuditService {

    @Autowired
    private CarPackageAuditMapper carPackageAuditMapper;

    @Override
    public TablePageInfo<CarPackageAudit> query(CarPackageAuditQueryObject qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<CarPackageAudit> list = carPackageAuditMapper.selectForList(qo);
        return new TablePageInfo<>(list);
    }

    @Override
    public CarPackageAudit get(Long id) {
        return carPackageAuditMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(CarPackageAudit carPackageAudit) {
        carPackageAudit.setCreateTime(new Date());
        carPackageAuditMapper.insert(carPackageAudit);
    }

    @Override
    public void update(CarPackageAudit carPackageAudit) {
        carPackageAuditMapper.updateByPrimaryKey(carPackageAudit);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] listId = ids.split(",");
        for (String id : listId) {
            carPackageAuditMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public List<CarPackageAudit> list() {
        return carPackageAuditMapper.selectAll();
    }

}
