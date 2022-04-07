package cn.wolfcode.car.business.mapper;

import cn.wolfcode.car.business.domain.Employee;
import cn.wolfcode.car.business.query.EmployeeQueryObject;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee emp);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee emp);

    List<Employee> selectForList(EmployeeQueryObject qo);
}