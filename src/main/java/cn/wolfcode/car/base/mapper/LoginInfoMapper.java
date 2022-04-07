package cn.wolfcode.car.base.mapper;

import cn.wolfcode.car.base.domain.LoginInfo;
import cn.wolfcode.car.base.query.LoginInfoQuery;

import java.util.List;

public interface LoginInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LoginInfo record);

    LoginInfo selectByPrimaryKey(Long id);

    List<LoginInfo> selectAll();

    int updateByPrimaryKey(LoginInfo record);

    List<LoginInfo> selectForList(LoginInfoQuery qo);

    void cleanLogininfo();
}