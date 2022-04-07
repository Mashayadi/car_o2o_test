package cn.wolfcode.car.business.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import lombok.*;

@Getter
@Setter
@ToString
public class CarPackageAuditQueryObject extends QueryObject {
    private String startTime;
    private String endTime;
}
