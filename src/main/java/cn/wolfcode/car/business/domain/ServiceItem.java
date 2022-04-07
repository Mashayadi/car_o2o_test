package cn.wolfcode.car.business.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceItem {

    public static final Integer CARPACKAGE_YES = 1;
    public static final Integer CARPACKAGE_NO = 0;

    public static final Integer AUDITSTATUS_INIT = 0;
    public static final Integer AUDITSTATUS_AUDIT = 1;
    public static final Integer AUDITSTATUS_APPROVE = 2;
    public static final Integer AUDITSTATUS_REPLY = 3;
    public static final Integer AUDITSTATUS_NO_REQUIRED = 4;

    public static final Integer SALESTATUS_OFF = 0;
    public static final Integer SALESTATUS_ON = 1;


    private Long id;

    /** 服务项名称*/
    private String name;

    /** 服务项原价*/
    private BigDecimal originalPrice;

    /** 服务项折扣价*/
    private BigDecimal discountPrice;

    /** 是否套餐【是/否】*/
    private int carPackage;

    /** 备注信息*/
    private String info;

    /** 创建时间*/
    private Date createTime;

    /** 服务分类【维修/保养/其他】*/
    private Integer serviceCatalog;

    /** 审核状态【初始化/审核中/审核通过/审核拒绝/无需审核】*/
    private Integer auditStatus = AUDITSTATUS_INIT;

    /** 上架状态【已上架/未上架】*/
    private Integer saleStatus = SALESTATUS_OFF;

    /** 版本号*/
    private Long version = 0L;

}