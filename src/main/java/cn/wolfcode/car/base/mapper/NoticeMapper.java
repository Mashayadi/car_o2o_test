package cn.wolfcode.car.base.mapper;

import cn.wolfcode.car.base.domain.Notice;
import cn.wolfcode.car.base.query.NoticeQuery;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    Notice selectByPrimaryKey(Long id);

    List<Notice> selectAll();

    int updateByPrimaryKey(Notice record);

    List<Notice> selectForList(NoticeQuery qo);
}