package cn.wolfcode.car.business.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatementQueryObject extends QueryObject {
    private String customerName;
    private String customerPhone;
    private String licensePlate;
}
