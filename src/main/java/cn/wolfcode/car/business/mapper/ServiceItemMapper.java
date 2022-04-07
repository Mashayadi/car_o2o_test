package cn.wolfcode.car.business.mapper;

import cn.wolfcode.car.business.domain.ServiceItem;
import cn.wolfcode.car.business.query.ServiceItemQueryObject;

import java.util.List;

public interface ServiceItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ServiceItem record);

    ServiceItem selectByPrimaryKey(Long id);

    List<ServiceItem> selectAll();

    int updateByPrimaryKey(ServiceItem record);

    List<ServiceItem> selectForList(ServiceItemQueryObject qo);
}