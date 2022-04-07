package cn.wolfcode.car.business.service.impl;

import cn.wolfcode.car.business.domain.StatementItem;
import cn.wolfcode.car.business.mapper.StatementItemMapper;
import cn.wolfcode.car.business.query.StatementItemQueryObject;
import cn.wolfcode.car.business.service.IStatementItemService;
import cn.wolfcode.car.business.service.IStatementService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.exception.BusinessException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class StatementItemServiceImpl implements IStatementItemService {

    @Autowired
    private StatementItemMapper statementItemMapper;

    @Autowired
    private IStatementService statementService;

    @Override
    public TablePageInfo<StatementItem> query(StatementItemQueryObject qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<StatementItem> list = statementItemMapper.selectForList(qo);
        return new TablePageInfo<>(list);
    }

    @Override
    public StatementItem get(Long id) {
        return statementItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(StatementItem statementItem) {
        statementItemMapper.insert(statementItem);
    }

    @Override
    public void update(StatementItem statementItem) {
        statementItemMapper.updateByPrimaryKey(statementItem);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] listId = ids.split(",");
        for (String id : listId) {
            statementItemMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public List<StatementItem> list() {
        return statementItemMapper.selectAll();
    }

    @Override
    public void saveItem(List<StatementItem> items) {
        if (items != null && items.size() > 0) {
            StatementItem last = items.remove(items.size() - 1);
            statementItemMapper.deleteStatementId(last.getStatementId());

            if (items.size() > 0) {
                BigDecimal totalAmount = new BigDecimal("0.0");
                BigDecimal totalQuantity = new BigDecimal("0");
                for (StatementItem item : items) {

                    statementItemMapper.insert(item);

                    totalAmount = totalAmount.add(item.getItemPrice()
                            .multiply(
                                    new BigDecimal(
                                            item.getItemQuantity())
                            )
                    );
                    totalQuantity = totalQuantity.add(item.getItemPrice()
                            .multiply(
                                    new BigDecimal(
                                            item.getItemQuantity())
                            )
                    );
                }

                if (totalAmount.compareTo(last.getItemPrice()) < 0) {
                    throw new BusinessException("非法操作,优惠金额大于总金额。");
                }

                statementService.updateAomunt(
                        last.getStatementId(), totalAmount, totalQuantity, last.getItemPrice()
                );

            } else {
                statementService.updateAomunt(last.getStatementId(),
                        new BigDecimal("0.0"),  new BigDecimal("0"),new BigDecimal("0.0"));
            }

        } else {
            throw  new BusinessException("参数异常");
        }

    }
}
