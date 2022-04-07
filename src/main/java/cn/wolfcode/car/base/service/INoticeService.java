package cn.wolfcode.car.base.service;

import cn.wolfcode.car.base.domain.Notice;
import cn.wolfcode.car.base.query.NoticeQuery;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

/**
 * 通知服务接口
 */
public interface INoticeService {

    /**
     * 分页
     * @param qo
     * @return
     */
    TablePageInfo<Notice> query(NoticeQuery qo);


    /**
     * 保存
     * @param notice
     */
    void save(Notice notice);


    /**
     * 查单个
     * @param id
     * @return
     */
    Notice get(Long id);

    /**
     * 更新
     * @param notice
     */
    void update(Notice notice);

    /**
     *  批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 查询全部
     * @return
     */
    List<Notice> list();
    
}
