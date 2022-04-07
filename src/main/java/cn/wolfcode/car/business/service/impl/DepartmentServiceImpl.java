package cn.wolfcode.car.business.service.impl;

import cn.wolfcode.car.business.domain.Department;
import cn.wolfcode.car.business.mapper.DepartmentMapper;
import cn.wolfcode.car.business.query.DepartmentQueryObject;
import cn.wolfcode.car.business.service.IDepartmentService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public TablePageInfo<Department> query(DepartmentQueryObject qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<Department> list = departmentMapper.selectForList(qo);
        return new TablePageInfo<>(list);
    }

    @Override
    public Department get(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void update(Department department) {
        departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] listId = ids.split(",");
        for (String id : listId) {
            departmentMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public List<Department> list() {
        return departmentMapper.selectAll();
    }


}
