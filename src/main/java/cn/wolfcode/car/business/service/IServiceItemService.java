package cn.wolfcode.car.business.service;

import cn.wolfcode.car.business.domain.ServiceItem;
import cn.wolfcode.car.business.query.ServiceItemQueryObject;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

public interface IServiceItemService {

    TablePageInfo<ServiceItem> query(ServiceItemQueryObject qo);

    ServiceItem get(Long id);

    void save(ServiceItem serviceItem);

    void update(ServiceItem serviceItem);

    void deleteBatch(String ids);

    List<ServiceItem> list();

    void saleOff(Long id);

    void saleOn(Long id);
}
