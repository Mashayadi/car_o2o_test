package cn.wolfcode.car.base.mapper;

import cn.wolfcode.car.base.domain.DictData;
import cn.wolfcode.car.base.query.DictDataQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DictData record);

    DictData selectByPrimaryKey(Long id);

    List<DictData> selectAll();

    int updateByPrimaryKey(DictData record);

    List<DictData> selectForList(DictDataQuery qo);

    List<DictData> selectByType(String dictType);

    DictData selectDictLabel(@Param("dictType") String dictType, @Param("dictValue")String dictValue);
}