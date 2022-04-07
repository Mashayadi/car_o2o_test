package cn.wolfcode.car.common.base.query;


import lombok.Getter;
import lombok.Setter;

/**
 * 分页+条件查询基类
 */
@Setter
@Getter
public class QueryObject {
    private int pageNum = 1;
    private int pageSize = 10;
}
