package cn.wolfcode.car.business.domain;

import lombok.*;

import java.math.BigDecimal;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatementItem {


    private Long id;

    /** 结算单ID*/
    private Long statementId;

    /** 服务项明细ID*/
    private Long itemId;

    /** 服务项明细名称*/
    private String itemName;

    /** 服务项价格*/
    private BigDecimal itemPrice;

    /** 购买数量*/
    private Long itemQuantity;

}