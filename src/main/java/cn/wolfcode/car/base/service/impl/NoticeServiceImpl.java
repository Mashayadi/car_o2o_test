package cn.wolfcode.car.base.service.impl;

import cn.wolfcode.car.base.domain.Notice;
import cn.wolfcode.car.base.mapper.NoticeMapper;
import cn.wolfcode.car.base.query.NoticeQuery;
import cn.wolfcode.car.base.service.INoticeService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.util.Convert;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public TablePageInfo<Notice> query(NoticeQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<Notice>(noticeMapper.selectForList(qo));
    }

    @Override
    public void save(Notice notice) {
        notice.setCreateTime(new Date());
        noticeMapper.insert(notice);
    }

    @Override
    public Notice get(Long id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Notice notice) {
        noticeMapper.updateByPrimaryKey(notice);
    }

    @Override
    public void deleteBatch(String ids) {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            Notice notice = this.get(dictId);
            noticeMapper.deleteByPrimaryKey(dictId);
        }
    }

    @Override
    public List<Notice> list() {
        return noticeMapper.selectAll();
    }
}
