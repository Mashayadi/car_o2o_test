package cn.wolfcode.car.business.service.impl;

import cn.wolfcode.car.business.domain.Customer;
import cn.wolfcode.car.business.mapper.CustomerMapper;
import cn.wolfcode.car.business.query.CustomerQueryObject;
import cn.wolfcode.car.business.service.ICustomerService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public TablePageInfo<Customer> query(CustomerQueryObject qo) {
        PageHelper.offsetPage(qo.getPageNum(),qo.getPageSize());
        List<Customer> list = customerMapper.selectForList(qo);
        return new TablePageInfo<>(list);
    }

    @Override
    public Customer get(Long id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Customer customer) {
        customerMapper.insert(customer);
    }

    @Override
    public void update(Customer customer) {
        customerMapper.updateByPrimaryKey(customer);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] listId = ids.split(",");
        for (String id : listId) {
            customerMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public List<Customer> list() {
        return customerMapper.selectAll();
    }

}
