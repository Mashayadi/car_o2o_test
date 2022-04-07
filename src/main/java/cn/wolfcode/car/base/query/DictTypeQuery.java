package cn.wolfcode.car.base.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 字典类型查询对象
 */
@Setter
@Getter
public class DictTypeQuery extends QueryObject {
    private String name;        //字典名
    private String type;        //字典类型
    private Character status;   //字典状态
}
