package cn.wolfcode.car.business.service;

import cn.wolfcode.car.business.domain.Department;
import cn.wolfcode.car.business.query.DepartmentQueryObject;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

public interface IDepartmentService {

    TablePageInfo<Department> query(DepartmentQueryObject qo);

    Department get(Long id);

    void save(Department department);

    void update(Department department);

    void deleteBatch(String ids);

    List<Department> list();

}
