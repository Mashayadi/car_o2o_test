package cn.wolfcode.car.base.service.impl;

import cn.wolfcode.car.base.domain.Menu;
import cn.wolfcode.car.base.domain.Post;
import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.mapper.MenuMapper;
import cn.wolfcode.car.base.query.MenuQuery;
import cn.wolfcode.car.base.service.IMenuService;
import cn.wolfcode.car.common.base.domain.Ztree;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.exception.BusinessException;
import cn.wolfcode.car.common.util.Convert;
import cn.wolfcode.car.common.util.StringUtils;
import cn.wolfcode.car.common.web.AjaxResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public TablePageInfo<Menu> query(MenuQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<Menu>(menuMapper.selectForList(qo));
    }

    @Override
    public List<Menu> list() {
        List<Menu> menus = menuMapper.selectAll();
        return getChildPerms(menus, 0L);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<Menu> getChildPerms(List<Menu> list, Long parentId) {
        List<Menu> returnList = new ArrayList<Menu>();
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext(); ) {
            Menu t = (Menu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (parentId.equals(t.getParentId())) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<Menu> list, Menu t) {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (Menu tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<Menu> getChildList(List<Menu> list, Menu t) {
        List<Menu> tlist = new ArrayList<Menu>();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext()) {
            Menu n = (Menu) it.next();
            if (t.getId().equals(n.getParentId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Menu> list, Menu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    @Override
    public Menu get(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void update(Menu menu) {
        menuMapper.updateByPrimaryKey(menu);
    }

    @Override
    public void deleteBatch(String ids) {
        Long[] idsA = Convert.toLongArray(ids);
        for (Long id : idsA) {
            menuMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Menu> list(MenuQuery qo) {
        return menuMapper.selectForList(qo);
    }

    @Override
    public List<Ztree> queryMenuTreeData(MenuQuery qo) {
        //List<Menu> menuList = selectMenuAll(userId);
        List<Menu> menuList = this.list(qo);
        ;
        if (qo.getRoleId() == null) {
            return initZtree(menuList);
        } else {
            //选中的菜单
            List<Long> menuIds = menuMapper.selectByRoleId(qo.getRoleId());
            return initZtree(menuList, menuIds, false);
        }

    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList) {
        return initZtree(menuList, null, false);
    }

    /**
     * 对象转菜单树
     *
     * @param menuList     菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag    是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList, List<Long> roleMenuList, boolean permsFlag) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (Menu menu : menuList) {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getName());
            if (roleMenuList != null) {
                ztree.setChecked(roleMenuList.contains(menu.getId()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(Menu menu, boolean permsFlag) {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }

    @Override
    public boolean checkNameUnique(Long parentId, String name) {
        return menuMapper.selectByParentIdAndName(parentId, name) != null;
    }

    @Override
    public void delete(Long id) {
        if (menuMapper.selectByParentId(id).size() > 0) {
            throw new BusinessException("存在子菜单,不允许删除");
        }
        /*if (menuService.selectCountRoleMenuByMenuId(menuId) > 0)
        {
            throw new BusinessException("菜单已分配,不允许删除");
        }*/
        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Menu> queryByUserId(Long userId) {
        return menuMapper.selectByUserId(userId);
    }

    @Override
    public List<Menu>  queryMenuTreeUser(User user) {
        List<Menu> menus = new ArrayList<>();
        if("admin".equals(user.getLoginName())){
            menus = menuMapper.selectAll();
        }else{
            menus = menuMapper.selectByUserId(user.getId());
        }
        return getChildPerms(menus, 0L);
    }
}
