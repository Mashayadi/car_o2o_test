package cn.wolfcode.car.business.mapper;

import cn.wolfcode.car.business.domain.CarPackageAudit;
import cn.wolfcode.car.business.query.CarPackageAuditQueryObject;

import java.util.List;

public interface CarPackageAuditMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CarPackageAudit record);

    CarPackageAudit selectByPrimaryKey(Long id);

    List<CarPackageAudit> selectAll();

    int updateByPrimaryKey(CarPackageAudit record);

    List<CarPackageAudit> selectForList(CarPackageAuditQueryObject qo);
}