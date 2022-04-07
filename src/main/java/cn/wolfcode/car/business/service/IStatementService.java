package cn.wolfcode.car.business.service;

import cn.wolfcode.car.business.domain.Statement;
import cn.wolfcode.car.business.query.StatementQueryObject;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.math.BigDecimal;
import java.util.List;

public interface IStatementService {

    TablePageInfo<Statement> query(StatementQueryObject qo);

    Statement get(Long id);

    void save(Statement statement);

    void update(Statement statement);

    void deleteBatch(String ids);

    List<Statement> list();

    void updateAomunt(Long statementId, BigDecimal totalAmount, BigDecimal totalQuantity, BigDecimal itemPrice);

    void payStatement(Long statementId);
}
