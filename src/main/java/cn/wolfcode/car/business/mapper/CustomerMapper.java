package cn.wolfcode.car.business.mapper;

import cn.wolfcode.car.business.domain.Customer;
import cn.wolfcode.car.business.query.CustomerQueryObject;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Long id);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);

    List<Customer> selectForList(CustomerQueryObject qo);
}