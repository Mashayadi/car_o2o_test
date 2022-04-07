package cn.wolfcode.car.base.mapper;

import cn.wolfcode.car.base.domain.Config;
import cn.wolfcode.car.base.domain.Dept;
import cn.wolfcode.car.base.query.ConfigQuery;
import cn.wolfcode.car.base.query.DeptQuery;

import java.util.Collection;
import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dept record);

    Dept selectByPrimaryKey(Long id);

    List<Dept> selectAll();

    int updateByPrimaryKey(Dept record);

    List<Dept> selectForList(DeptQuery qo);

    List<Dept> selectWithQo(DeptQuery qo);

    Dept selectByName(String name);

    List<Dept> selectChildren(Long parentId);

    List<Long> selectIdByRoleId(Long roleId);
}