package cn.wolfcode.car.base.service.impl;

import cn.wolfcode.car.base.domain.DictData;
import cn.wolfcode.car.base.domain.DictType;
import cn.wolfcode.car.base.mapper.DictTypeMapper;
import cn.wolfcode.car.base.query.DictTypeQuery;
import cn.wolfcode.car.base.service.IDictDataService;
import cn.wolfcode.car.base.service.IDictTypeService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.exception.BusinessException;
import cn.wolfcode.car.common.util.Convert;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DictTypeServiceImpl implements IDictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private IDictDataService dictDataService;


    @Override
    public TablePageInfo<DictType> query(DictTypeQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<DictType>(dictTypeMapper.selectForList(qo));
    }


    @Override
    public void save(DictType dictType) {
        dictTypeMapper.insert(dictType);
    }


    @Override
    public boolean checkTypeExsit(String type) {
        return dictTypeMapper.selectByType(type) > 0;
    }

    @Override
    public DictType get(Long id) {
        return dictTypeMapper.selectByPrimaryKey(id);
    }


    @Override
    public void update(DictType dictType) {
        dictTypeMapper.updateByPrimaryKey(dictType);
    }

    @Override
    public void deleteBatch(String ids) {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            DictType dictType = this.get(dictId);
            if (dictDataService.queryByType(dictType.getType()).size() > 0){
                throw new BusinessException(String.format("%1$s已分配,不能删除", dictType.getName()));
            }
            dictTypeMapper.deleteByPrimaryKey(dictId);
        }
    }

    @Override
    public List<DictType> list() {
        return dictTypeMapper.selectAll();
    }
}
