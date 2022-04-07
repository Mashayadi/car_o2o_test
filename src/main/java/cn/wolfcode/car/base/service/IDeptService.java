package cn.wolfcode.car.base.service;

import cn.wolfcode.car.base.domain.Dept;
import cn.wolfcode.car.base.query.DeptQuery;
import cn.wolfcode.car.common.base.domain.Ztree;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

/**
 * 部门服务接口
 */
public interface IDeptService {

    /**
     * 分页
     * @param qo
     * @return
     */
    TablePageInfo<Dept> query(DeptQuery qo);


    /**
     * 保存
     * @param dept
     */
    void save(Dept dept);


    /**
     * 查单个
     * @param id
     * @return
     */
    Dept get(Long id);

    /**
     * 更新
     * @param dept
     */
    void update(Dept dept);

    /**
     *  批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 查询全部
     * @return
     */
    List<Dept> list();

    /**
     * 条件查询
     * @param qo
     * @return
     */
    List<Dept> list(DeptQuery qo);

    /**
     * 部门树状数据结构
     * @return
     * @param qo
     */
    List<Ztree> selectDeptTree(DeptQuery qo);

    /**
     * 判断name是否存在
     * @param name
     * @return true: 存在， false：不存在
     */
    boolean checkNameExsit(String name);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 角色部门权限
     * @param roleId
     * @return
     */
    List<Ztree> roleDeptTreeData(Long roleId);
}
