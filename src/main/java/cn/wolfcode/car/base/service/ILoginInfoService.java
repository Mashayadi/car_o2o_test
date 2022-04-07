package cn.wolfcode.car.base.service;

import cn.wolfcode.car.base.domain.LoginInfo;
import cn.wolfcode.car.base.query.LoginInfoQuery;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

/**
 * 登录信息服务接口
 */
public interface ILoginInfoService {

    /**
     * 分页
     * @param qo
     * @return
     */
    TablePageInfo<LoginInfo> query(LoginInfoQuery qo);

    /**
     * 查单个
     * @param id
     * @return
     */
    LoginInfo get(Long id);


    /**
     * 保存
     * @param loginInfo
     */
    void save(LoginInfo loginInfo);

  
    /**
     * 更新
     * @param loginInfo
     */
    void update(LoginInfo loginInfo);

    /**
     *  批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 查询全部
     * @return
     */
    List<LoginInfo> list();


    /**
     * 清空表
     */
    void cleanLogininfo();
}
