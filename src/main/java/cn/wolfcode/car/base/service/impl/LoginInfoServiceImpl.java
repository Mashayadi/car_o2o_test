package cn.wolfcode.car.base.service.impl;

import cn.wolfcode.car.base.domain.LoginInfo;
import cn.wolfcode.car.base.mapper.LoginInfoMapper;
import cn.wolfcode.car.base.query.LoginInfoQuery;
import cn.wolfcode.car.base.service.ILoginInfoService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.util.Convert;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoginInfoServiceImpl implements ILoginInfoService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;


    @Override
    public TablePageInfo<LoginInfo> query(LoginInfoQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<LoginInfo>(loginInfoMapper.selectForList(qo));
    }


    @Override
    public void save(LoginInfo loginInfo) {
        loginInfoMapper.insert(loginInfo);
    }

    @Override
    public LoginInfo get(Long id) {
        return loginInfoMapper.selectByPrimaryKey(id);
    }


    @Override
    public void update(LoginInfo loginInfo) {
        loginInfoMapper.updateByPrimaryKey(loginInfo);
    }

    @Override
    public void deleteBatch(String ids) {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            loginInfoMapper.deleteByPrimaryKey(dictId);
        }
    }

    @Override
    public List<LoginInfo> list() {
        return loginInfoMapper.selectAll();
    }


    @Override
    public void cleanLogininfo() {
        loginInfoMapper.cleanLogininfo();
    }
}
