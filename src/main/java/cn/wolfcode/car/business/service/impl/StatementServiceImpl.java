package cn.wolfcode.car.business.service.impl;

import cn.wolfcode.car.business.domain.Statement;
import cn.wolfcode.car.business.mapper.StatementMapper;
import cn.wolfcode.car.business.query.StatementQueryObject;
import cn.wolfcode.car.business.service.IStatementService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.exception.BusinessException;
import cn.wolfcode.car.shiro.ShiroUtils;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StatementServiceImpl implements IStatementService {

    @Autowired
    private StatementMapper statementMapper;

    @Override
    public TablePageInfo<Statement> query(StatementQueryObject qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<Statement> list = statementMapper.selectForList(qo);
        return new TablePageInfo<>(list);
    }

    @Override
    public Statement get(Long id) {
        return statementMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Statement statement) {
        statement.setCreateTime(new Date());
        statement.setStatus(statement.STATUS_CONSUME);
        statementMapper.insert(statement);
    }

    @Override
    public void update(Statement statement) {
        statementMapper.updateByStatement(statement);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] listId = ids.split(",");
        for (String id : listId) {
            statementMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public List<Statement> list() {
        return statementMapper.selectAll();
    }

    @Override
    public void updateAomunt(Long statementId, BigDecimal totalAmount, BigDecimal totalQuantity, BigDecimal itemPrice) {
        statementMapper.updateAomunt(statementId,totalAmount,totalQuantity,itemPrice);
    }

    @Override
    public void payStatement(Long statementId) {

        Statement statement = statementMapper.selectByPrimaryKey(statementId);

        if (!Statement.STATUS_CONSUME.equals(statement.getStatus())) {
            throw new BusinessException("不能重复支付。");
        }

        statement.setStatus(Statement.STATUS_PAID);
        statement.setPayTime(new Date());
        statement.setPayeeId(ShiroUtils.getUserId());

        statementMapper.payStatement(statement);
    }
}
