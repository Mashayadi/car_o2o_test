package cn.wolfcode.car.base.mapper;

import cn.wolfcode.car.base.domain.Role;
import cn.wolfcode.car.base.query.RoleQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectForList(RoleQuery qo);

    Role selectByName(String name);

    Role selectByKey(String rkey);

    void insertMenuRelation(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    void deleteMenuRelation(Long roleId);

    void changeStatus(@Param("roleId")Long roleId,@Param("status") String status);

    List<Long> selectUserIdByRoleId(Long roleId);

    List<Role> selectByUserId(Long userId);

    void updateDataRole(Role role);

    void deleteDeptRelation(Long roleId);
    void insertDeptRelation(@Param("roleId")Long roleId, @Param("deptId")Long deptId);

    void insertUserRelation(@Param("roleId")Long roleId, @Param("userId")Long userId);
    void deleteUserRelation(@Param("roleId")Long roleId, @Param("userId")Long userId);
}