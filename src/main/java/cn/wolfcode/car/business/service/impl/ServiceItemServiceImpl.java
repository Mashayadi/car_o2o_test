package cn.wolfcode.car.business.service.impl;

import cn.wolfcode.car.business.domain.ServiceItem;
import cn.wolfcode.car.business.mapper.ServiceItemMapper;
import cn.wolfcode.car.business.query.ServiceItemQueryObject;
import cn.wolfcode.car.business.service.IServiceItemService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.exception.BusinessException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServiceItemServiceImpl implements IServiceItemService {

    @Autowired
    private ServiceItemMapper serviceItemMapper;

    @Override
    public TablePageInfo<ServiceItem> query(ServiceItemQueryObject qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<ServiceItem> list = serviceItemMapper.selectForList(qo);
        return new TablePageInfo<>(list);
    }

    @Override
    public ServiceItem get(Long id) {
        return serviceItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(ServiceItem serviceItem) {

        if (serviceItem.CARPACKAGE_YES.equals(serviceItem.getCarPackage())) {
            serviceItem.setAuditStatus(serviceItem.AUDITSTATUS_AUDIT);
        } else {
            serviceItem.setAuditStatus(serviceItem.AUDITSTATUS_NO_REQUIRED);
        }
        serviceItem.setCreateTime(new Date());
        serviceItem.setSaleStatus(serviceItem.SALESTATUS_OFF);

        serviceItemMapper.insert(serviceItem);
    }

    @Override
    public void update(ServiceItem serviceItem) {

        ServiceItem item = serviceItemMapper.selectByPrimaryKey(serviceItem.getId());

        if (!serviceItem.SALESTATUS_OFF.equals(item.getSaleStatus())) {
            throw new BusinessException("请将服务项下架后再进行操作。");
        }

        if (!(serviceItem.AUDITSTATUS_NO_REQUIRED.equals(item.getAuditStatus())
                || (serviceItem.AUDITSTATUS_APPROVE.equals(item.getAuditStatus())))) {
            throw new BusinessException("服务项正在审核中，不能进行操作。");
        }


        item.setName(serviceItem.getName());
        item.setOriginalPrice(serviceItem.getOriginalPrice());
        item.setDiscountPrice(serviceItem.getDiscountPrice());
        item.setCarPackage(serviceItem.getCarPackage());
        item.setServiceCatalog(serviceItem.getServiceCatalog());
        item.setInfo(serviceItem.getInfo());


        serviceItemMapper.updateByPrimaryKey(serviceItem);
    }

    @Override
    public void deleteBatch(String ids) {

        String[] listId = ids.split(",");
        for (String id : listId) {
            ServiceItem item = serviceItemMapper.selectByPrimaryKey(Long.valueOf(id));
            if (!item.SALESTATUS_OFF.equals(item.getSaleStatus())) {
                throw new BusinessException("请将服务项下架后再进行操作。");
            }

            if (!(item.AUDITSTATUS_NO_REQUIRED.equals(item.getAuditStatus())
                    || (item.AUDITSTATUS_APPROVE.equals(item.getAuditStatus())))) {
                throw new BusinessException("服务项正在审核中，不能进行操作。");
            }
            serviceItemMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public List<ServiceItem> list() {
        return serviceItemMapper.selectAll();
    }

    @Override
    public void saleOff(Long id) {
        ServiceItem serviceItem = serviceItemMapper.selectByPrimaryKey(id);

        if (!(serviceItem.AUDITSTATUS_NO_REQUIRED.equals(serviceItem.getAuditStatus())
                || (serviceItem.AUDITSTATUS_APPROVE.equals(serviceItem.getAuditStatus())))) {
            throw new BusinessException("服务项正在审核中，不能进行操作。");
        }
        serviceItem.setSaleStatus(serviceItem.SALESTATUS_OFF);
        serviceItemMapper.updateByPrimaryKey(serviceItem);
    }

    @Override
    public void saleOn(Long id) {
        ServiceItem serviceItem = serviceItemMapper.selectByPrimaryKey(id);

        if (!(serviceItem.AUDITSTATUS_NO_REQUIRED.equals(serviceItem.getAuditStatus())
                || (serviceItem.AUDITSTATUS_APPROVE.equals(serviceItem.getAuditStatus())))) {
            throw new BusinessException("服务项正在审核中，不能进行操作。");
        }

        serviceItem.setSaleStatus(serviceItem.SALESTATUS_ON);
        serviceItemMapper.updateByPrimaryKey(serviceItem);
    }
}
