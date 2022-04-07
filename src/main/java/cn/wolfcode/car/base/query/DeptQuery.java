package cn.wolfcode.car.base.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 部门查询对象
 */
@Setter
@Getter
public class DeptQuery extends QueryObject {
    private Long  parentId;
    private String  name;
    private Character  status;

}
