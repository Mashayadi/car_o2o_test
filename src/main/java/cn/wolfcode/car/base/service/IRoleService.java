package cn.wolfcode.car.base.service;

import cn.wolfcode.car.base.domain.Role;
import cn.wolfcode.car.base.query.RoleQuery;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

/**
 * 角色服务接口
 */
public interface IRoleService {

    /**
     * 分页
     * @param qo
     * @return
     */
    TablePageInfo<Role> query(RoleQuery qo);


    /**
     * 保存
     * @param role
     */
    void save(Role role);


    /**
     * 查单个
     * @param id
     * @return
     */
    Role get(Long id);

    /**
     * 更新
     * @param role
     */
    void update(Role role);

    /**
     *  批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 查询全部
     * @return
     */
    List<Role> list();


    /**
     * 检查name是否存在
     * @param name
     * @return
     */
    boolean checkNameExsit(String name);
    /**
     * 检查key是否存在
     * @param rkey
     * @return
     */
    boolean checkKeyExsit(String rkey);

    /**
     * 修改状态
     * @param roleId
     * @param status
     */
    void changeStatus(Long roleId, String status);

    /**
     * 查询用户角色集合
     * @param userId
     * @return
     */
    List<Role> queryByUserId(Long userId);


    /**
     * 分配权限
     * @param role
     */
    void authDataScope(Role role);

    /**
     * 分配用户：角色与 用户
     * @param roleId
     * @param userIds
     */
    void insertAuthUsers(Long roleId, String userIds);

    /**
     * 取消授权
     * @param roleId
     * @param userIds
     */
    void deleteAuthUser(Long roleId, String userIds);

    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    List<Role> listAllWithUserId(Long userId);
}
