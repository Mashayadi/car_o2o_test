package cn.wolfcode.car.base.service;

import cn.wolfcode.car.base.domain.Config;
import cn.wolfcode.car.base.query.ConfigQuery;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

/**
 * 字典服务接口
 */
public interface IConfigService {

    /**
     * 分页
     * @param qo
     * @return
     */
    TablePageInfo<Config> query(ConfigQuery qo);


    /**
     * 保存
     * @param config
     */
    void save(Config config);


    /**
     * 查单个
     * @param id
     * @return
     */
    Config get(Long id);

    /**
     * 更新
     * @param config
     */
    void update(Config config);

    /**
     *  批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 查询全部
     * @return
     */
    List<Config> list();

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    boolean checkKeyExsit(String key);

    /**
     * 根据键名查询参数配置信息
     *
     * @param key 参数键名
     * @return 参数键值
     */
    String getValue(String key);
}
