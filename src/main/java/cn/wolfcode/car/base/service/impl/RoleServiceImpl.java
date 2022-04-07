package cn.wolfcode.car.base.service.impl;

import cn.wolfcode.car.base.domain.Role;
import cn.wolfcode.car.base.mapper.RoleMapper;
import cn.wolfcode.car.base.query.RoleQuery;
import cn.wolfcode.car.base.service.IRoleService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.exception.BusinessException;
import cn.wolfcode.car.common.util.Convert;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;






    @Override
    public TablePageInfo<Role> query(RoleQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<Role>(roleMapper.selectForList(qo));
    }

    @Override
    public void save(Role role) {
        role.setCreateTime(new Date());
        roleMapper.insert(role);

        //维护关系（角色与菜单）
        List<Long> menuIds = role.getMenuIds();
        for (Long menuId : menuIds) {
            roleMapper.insertMenuRelation(role.getId(), menuId);
        }
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Role role) {

        //解除关系
        roleMapper.deleteMenuRelation(role.getId());
        roleMapper.updateByPrimaryKey(role);
        List<Long> menuIds = role.getMenuIds();

        //重新维护关系
        for (Long menuId : menuIds) {
            roleMapper.insertMenuRelation(role.getId(), menuId);
        }


    }
    @Override
    public void deleteBatch(String ids) {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            Role role = this.get(dictId);
            if (roleMapper.selectUserIdByRoleId(role.getId()).size() > 0){
                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getName()));
            }
            //解除关系（角色与菜单）
            roleMapper.deleteMenuRelation(role.getId());
            roleMapper.deleteByPrimaryKey(dictId);
        }
    }
    @Override
    public List<Role> list() {
        return roleMapper.selectAll();
    }

    @Override
    public boolean checkNameExsit(String name) {
        return roleMapper.selectByName(name) != null;
    }

    @Override
    public boolean checkKeyExsit(String rkey) {
        return roleMapper.selectByKey(rkey) != null;
    }

    @Override
    public void changeStatus(Long roleId, String status) {
        roleMapper.changeStatus(roleId, status);
    }

    @Override
    public List<Role> queryByUserId(Long userId) {
        return roleMapper.selectByUserId(userId);
    }

    @Override
    public void authDataScope(Role role) {
        if(role.getId() != null && "admin".equals(role.getRkey())){
            throw  new BusinessException("超级管理员不能修改");
        }
        // 修改角色信息
        roleMapper.updateDataRole(role);
        // 删除角色与部门关联
        roleMapper.deleteDeptRelation(role.getId());
        // 新增角色和部门信息（数据权限）

        List<Long> deptIds = role.getDeptIds();
        if (deptIds!= null && deptIds.size()>0){
            for (Long deptId : deptIds) {
                roleMapper.insertDeptRelation(role.getId(), deptId);
            }
        }
    }

    @Override
    public void insertAuthUsers(Long roleId, String userIds) {
        Long[] users = Convert.toLongArray(userIds);

        for (Long userId : users) {
            roleMapper.insertUserRelation(roleId, userId);
        }
    }

    @Override
    public void deleteAuthUser(Long roleId, String userIds) {
        Long[] ids = Convert.toLongArray(userIds);
        if(ids != null && ids.length > 0){
            for (Long id : ids) {
                roleMapper.deleteUserRelation(roleId, id);
            }

        }
    }

    @Override
    public List<Role> listAllWithUserId(Long userId) {
        List<Role> roles = roleMapper.selectAll();

        List<Role> userRoles = this.queryByUserId(userId);
        List<Long> collect = userRoles.stream().map(r -> r.getId()).collect(Collectors.toList());
        for (Role role : roles) {
            if(collect.contains(role.getId())){
                role.setFlag(true);
            }
        }


        return roles;
    }
}
