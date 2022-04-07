package cn.wolfcode.car.base.service.impl;

import cn.wolfcode.car.base.domain.DictData;
import cn.wolfcode.car.base.mapper.DictDataMapper;
import cn.wolfcode.car.base.query.DictDataQuery;
import cn.wolfcode.car.base.service.IDictDataService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.util.Convert;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dict")
@Transactional
public class DictDataServiceImpl implements IDictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<DictData> getType(String dictType){
        return this.queryByType(dictType);
    }
    @Override
    public List<DictData> queryByType(String dictType) {
        return dictDataMapper.selectByType(dictType);
    }


    @Override
    public TablePageInfo<DictData> query(DictDataQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<DictData>(dictDataMapper.selectForList(qo));
    }



    @Override
    public String queryDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue).getLabel();
    }


    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue)    {
        return this.queryDictLabel(dictType, dictValue);
    }

    @Override
    public DictData queryDictData(String type, String value) {
        return dictDataMapper.selectDictLabel(type, value);
    }

    @Override
    public List<DictData> list() {
        return dictDataMapper.selectAll();
    }

    @Override
    public void save(DictData data) {
        dictDataMapper.insert(data);
    }

    @Override
    public DictData get(Long id) {
        return dictDataMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(DictData data) {
        dictDataMapper.updateByPrimaryKey(data);
    }

    @Override
    public void deleteBatch(String ids) {
        Long[] idsA = Convert.toLongArray(ids);
        for (Long id : idsA){
            dictDataMapper.deleteByPrimaryKey(id);
        }
    }
}
