package cn.wolfcode.car.base.mapper;

import cn.wolfcode.car.base.domain.Menu;
import cn.wolfcode.car.base.query.MenuQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface MenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<Menu> selectForList(MenuQuery qo);

    Menu selectByParentIdAndName(@Param("parentId") Long parentId, @Param("name")String name);

    /**
     * 查询子菜单
     * @param parentId
     * @return
     */
    List<Menu> selectByParentId(Long parentId);

    List<Long> selectByRoleId(Long roleId);

    List<Menu> selectByUserId(Long userId);
}