package cn.wolfcode.car.base.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 通告查询对象
 */
@Setter
@Getter
public class NoticeQuery extends QueryObject {

    private Character type;
    private String createBy;
    private String title;
}
