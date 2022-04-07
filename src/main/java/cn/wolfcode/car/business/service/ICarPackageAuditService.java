package cn.wolfcode.car.business.service;

import cn.wolfcode.car.business.domain.CarPackageAudit;
import cn.wolfcode.car.business.query.CarPackageAuditQueryObject;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

public interface ICarPackageAuditService {

    TablePageInfo<CarPackageAudit> query(CarPackageAuditQueryObject qo);

    CarPackageAudit get(Long id);

    void save(CarPackageAudit carPackageAudit);

    void update(CarPackageAudit carPackageAudit);

    void deleteBatch(String ids);

    List<CarPackageAudit> list();

}
