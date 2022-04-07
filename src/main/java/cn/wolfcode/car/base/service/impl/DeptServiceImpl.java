package cn.wolfcode.car.base.service.impl;

import cn.wolfcode.car.base.domain.Dept;
import cn.wolfcode.car.base.mapper.DeptMapper;
import cn.wolfcode.car.base.query.DeptQuery;
import cn.wolfcode.car.base.service.IDeptService;
import cn.wolfcode.car.base.service.IUserService;
import cn.wolfcode.car.common.base.domain.Ztree;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.exception.BusinessException;
import cn.wolfcode.car.common.util.Convert;
import cn.wolfcode.car.common.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private IUserService userService;


    @Override
    public TablePageInfo<Dept> query(DeptQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<Dept>(deptMapper.selectForList(qo));
    }


    @Override
    public void save(Dept dept) {
        Dept info = deptMapper.selectByPrimaryKey(dept.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if ('0' != info.getStatus()){
            throw new BusinessException("部门停用，不允许新增");
        }
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        deptMapper.insert(dept);
    }




    @Override
    public Dept get(Long id) {
        return deptMapper.selectByPrimaryKey(id);
    }


    @Override
    public void update(Dept dept) {
        deptMapper.updateByPrimaryKey(dept);
    }

    @Override
    public void deleteBatch(String ids) {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            Dept dept = this.get(dictId);
            deptMapper.deleteByPrimaryKey(dictId);
        }
    }

    @Override
    public List<Dept> list() {
        return deptMapper.selectAll();
    }


    @Override
    public List<Dept> list(DeptQuery qo) {
        return deptMapper.selectWithQo(qo);
    }

    @Override
    public List<Ztree> selectDeptTree(DeptQuery qo) {
        List<Dept> list = this.list(qo);
        List<Ztree> ztrees = initZtree(list);
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Dept> deptList)
    {
        return initZtree(deptList, null);
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Dept> deptList, List<Long> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (Dept dept : deptList)
        {
            if (dept.getStatus() == '0'){
                Ztree ztree = new Ztree();
                ztree.setId(dept.getId());
                ztree.setpId(dept.getParentId());
                ztree.setName(dept.getName());
                ztree.setTitle(dept.getName());
                if (isCheck){
                    ztree.setChecked(roleDeptList.contains(dept.getId()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    @Override
    public boolean checkNameExsit(String name) {
        return deptMapper.selectByName(name) != null;
    }

    @Override
    public void delete(Long id) {
        if (deptMapper.selectChildren(id).size() > 0) {
            throw new BusinessException("存在下级部门,不允许删除");
        }
        if (userService.queryByDeptId(id).size() > 0){
            throw new BusinessException("部门存在用户,不允许删除");
        }
        deptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Ztree> roleDeptTreeData(Long roleId) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<Dept> deptList = deptMapper.selectAll();
        if (roleId != null){
            List<Long> deptIds = deptMapper.selectIdByRoleId(roleId);
            ztrees = initZtree(deptList, deptIds);
        }
        else
        {
            ztrees = initZtree(deptList);
        }
        return ztrees;
    }
}
