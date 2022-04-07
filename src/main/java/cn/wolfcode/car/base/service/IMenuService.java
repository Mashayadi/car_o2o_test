package cn.wolfcode.car.base.service;

import cn.wolfcode.car.base.domain.Menu;
import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.query.MenuQuery;
import cn.wolfcode.car.common.base.domain.Ztree;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

/**
 * 岗位服务接口
 */
public interface IMenuService {

    /**
     * 分页
     * @param qo
     * @return
     */
    TablePageInfo<Menu> query(MenuQuery qo);


    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> list();

    /**
     * 查单个
     * @param id
     * @return
     */
    Menu get(Long id);

    /**
     * 添加
     * @param menu
     */
    void save(Menu menu);

    /**
     * 更新
     * @param menu
     */
    void update(Menu menu);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 带条件查询列表
     * @param qo
     * @return
     */
    List<Menu> list(MenuQuery qo);

    /**
     * 菜单树
     * @param qo
     * @return
     */
    List<Ztree> queryMenuTreeData(MenuQuery qo);

    /**
     * 查询指定父菜单下name菜单名是否存在
     * @param parentId
     * @param name
     * @return
     */
    boolean checkNameUnique(Long parentId, String name);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 查询指定用户菜单
     * @param userId
     * @return
     */
    List<Menu> queryByUserId(Long userId);

    /**
     * 查询左侧菜单树
     * @param user
     * @return
     */
    List<Menu> queryMenuTreeUser(User user);

}
