package cn.wolfcode.car.base.service.impl;

import cn.wolfcode.car.base.domain.Config;
import cn.wolfcode.car.base.mapper.ConfigMapper;
import cn.wolfcode.car.base.query.ConfigQuery;
import cn.wolfcode.car.base.service.IConfigService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.util.Convert;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("config")
@Transactional
public class ConfigServiceImpl implements IConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public TablePageInfo<Config> query(ConfigQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<Config>(configMapper.selectForList(qo));
    }


    @Override
    public void save(Config config) {
        configMapper.insert(config);
    }




    @Override
    public Config get(Long id) {
        return configMapper.selectByPrimaryKey(id);
    }


    @Override
    public void update(Config config) {
        configMapper.updateByPrimaryKey(config);
    }

    @Override
    public void deleteBatch(String ids) {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            Config config = this.get(dictId);
            configMapper.deleteByPrimaryKey(dictId);
        }
    }

    @Override
    public List<Config> list() {
        return configMapper.selectAll();
    }

    @Override
    public boolean checkKeyExsit(String key) {
        return configMapper.selectByKey(key) != null;
    }



    @Override
    public String getValue(String key) {
        Config config = configMapper.selectByKey(key);
        return config.getValue();
    }
}
