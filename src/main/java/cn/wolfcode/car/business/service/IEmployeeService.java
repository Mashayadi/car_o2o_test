package cn.wolfcode.car.business.service;

import cn.wolfcode.car.business.domain.Employee;
import cn.wolfcode.car.business.query.EmployeeQueryObject;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

public interface IEmployeeService {

    TablePageInfo<Employee> query(EmployeeQueryObject qo);

    Employee get(Long id);

    void save(Employee employee);

    void update(Employee employee);

    void deleteBatch(String ids);

    List<Employee> list();
    
}
