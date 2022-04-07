package cn.wolfcode.car.business.service;

import cn.wolfcode.car.business.domain.StatementItem;
import cn.wolfcode.car.business.query.StatementItemQueryObject;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

public interface IStatementItemService {

    TablePageInfo<StatementItem> query(StatementItemQueryObject qo);

    StatementItem get(Long id);

    void save(StatementItem statementItem);

    void update(StatementItem statementItem);

    void deleteBatch(String ids);

    List<StatementItem> list();

    void saveItem(List<StatementItem> items);
}
