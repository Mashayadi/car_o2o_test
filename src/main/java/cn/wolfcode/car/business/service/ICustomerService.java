package cn.wolfcode.car.business.service;

import cn.wolfcode.car.business.domain.Customer;
import cn.wolfcode.car.business.query.CustomerQueryObject;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

public interface ICustomerService {

    TablePageInfo<Customer> query(CustomerQueryObject qo);

    Customer get(Long id);

    void save(Customer customer);

    void update(Customer customer);

    void deleteBatch(String ids);

    List<Customer> list();

}
