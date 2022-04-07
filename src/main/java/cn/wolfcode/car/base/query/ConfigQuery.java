package cn.wolfcode.car.base.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 蚕食查询对象
 */
@Setter
@Getter
public class ConfigQuery extends QueryObject {
    private String name;
    private String ckey;
    private Character type;
}
