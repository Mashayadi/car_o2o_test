package cn.wolfcode.car.base.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import com.alibaba.druid.sql.visitor.functions.Char;
import lombok.Getter;
import lombok.Setter;

/**
 * 岗位查询对象
 */
@Setter
@Getter
public class PostQuery extends QueryObject {
    private String code;
    private String name;

    private Character status;
}
