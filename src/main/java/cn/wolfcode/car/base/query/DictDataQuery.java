package cn.wolfcode.car.base.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import com.alibaba.druid.sql.visitor.functions.Char;
import lombok.Getter;
import lombok.Setter;

/**
 * 字典数据查询对象
 */
@Setter
@Getter
public class DictDataQuery extends QueryObject {
    private String type;        //字典类别
    private String label;       //字典数据标签
    private Character status;   //字典数据状态
}
