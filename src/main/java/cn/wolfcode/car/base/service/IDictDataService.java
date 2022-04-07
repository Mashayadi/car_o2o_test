package cn.wolfcode.car.base.service;

import cn.wolfcode.car.base.domain.DictData;
import cn.wolfcode.car.base.query.DictDataQuery;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.Collection;
import java.util.List;

/**
 * 字典数据服务接口
 */
public interface IDictDataService {

    /**
     * 分页
     * @param qo
     * @return
     */
    TablePageInfo<DictData> query(DictDataQuery qo);


    /**
     * 获取指定类型的字典数据列表
     * @param dictType
     * @return
     */
    List<DictData> queryByType(String dictType);

    /**
     * 获取 数据字典数据标签
     * @param dictType
     * @param dictValue
     * @return
     */
    String queryDictLabel(String dictType, String dictValue);

    /**
     * 查询所有
     * @return
     */
    List<DictData> list();

    /**
     * 添加
     * @param data
     */
    void save(DictData data);

    /**
     * 查单个
     * @param id
     * @return
     */
    DictData get(Long id);

    /**
     * 添加
     * @param data
     */
    void update(DictData data);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 通过type 跟 value查对象
     * @param type
     * @param value
     * @return
     */
    DictData queryDictData(String type, String value);
}
