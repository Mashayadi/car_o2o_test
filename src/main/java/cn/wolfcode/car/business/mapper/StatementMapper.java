package cn.wolfcode.car.business.mapper;

import cn.wolfcode.car.business.domain.Statement;
import cn.wolfcode.car.business.query.StatementQueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface StatementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Statement record);

    Statement selectByPrimaryKey(Long id);

    List<Statement> selectAll();

    int updateByPrimaryKey(Statement record);

    List<Statement> selectForList(StatementQueryObject qo);

    void updateByStatement(Statement statement);

    void updateAomunt(Long statementId, BigDecimal totalAmount, BigDecimal totalQuantity, BigDecimal itemPrice);

    void payStatement(Statement statement);
}