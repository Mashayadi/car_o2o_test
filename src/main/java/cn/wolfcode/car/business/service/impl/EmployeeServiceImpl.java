package cn.wolfcode.car.business.service.impl;

import cn.wolfcode.car.business.domain.Employee;
import cn.wolfcode.car.business.mapper.EmployeeMapper;
import cn.wolfcode.car.business.query.EmployeeQueryObject;
import cn.wolfcode.car.business.service.IEmployeeService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public TablePageInfo<Employee> query(EmployeeQueryObject qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<Employee> list = employeeMapper.selectForList(qo);
        return new TablePageInfo<>(list);
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Employee employee) {
        employeeMapper.insert(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] listId = ids.split(",");
        for (String id : listId) {
            employeeMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public List<Employee> list() {
        return employeeMapper.selectAll();
    }
}
