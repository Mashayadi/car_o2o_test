package cn.wolfcode.car.base.mapper;

import cn.wolfcode.car.base.domain.DictData;
import cn.wolfcode.car.base.domain.DictType;
import cn.wolfcode.car.base.query.DictTypeQuery;

import java.util.List;

public interface DictTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DictType record);

    DictType selectByPrimaryKey(Long id);

    List<DictType> selectAll();

    int updateByPrimaryKey(DictType record);

    List<DictType> selectForList(DictTypeQuery qo);


    int selectByType(String type);
}