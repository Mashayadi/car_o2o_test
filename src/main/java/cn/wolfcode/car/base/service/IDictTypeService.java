package cn.wolfcode.car.base.service;

import cn.wolfcode.car.base.domain.DictData;
import cn.wolfcode.car.base.domain.DictType;
import cn.wolfcode.car.base.query.DictTypeQuery;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

/**
 * 字典服务接口
 */
public interface IDictTypeService {

    /**
     * 分页
     * @param qo
     * @return
     */
    TablePageInfo<DictType> query(DictTypeQuery qo);


    /**
     * 保存
     * @param dictType
     */
    void save(DictType dictType);

    /**
     * 检查指定的type是否存在
     * @param type
     * @return true: 存在 false：不存在
     */
    boolean checkTypeExsit(String type);

    /**
     * 查单个
     * @param id
     * @return
     */
    DictType get(Long id);

    /**
     * 更新
     * @param dictType
     */
    void update(DictType dictType);

    /**
     *  批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 查询全部
     * @return
     */
    List<DictType> list();

}
