package cn.wolfcode.car.base.mapper;

import cn.wolfcode.car.base.domain.Config;
import cn.wolfcode.car.base.query.ConfigQuery;

import java.util.List;

public interface ConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Config record);

    Config selectByPrimaryKey(Long id);

    List<Config> selectAll();

    int updateByPrimaryKey(Config record);


    List<Config> selectForList(ConfigQuery qo);

    Config selectByKey(String key);
}