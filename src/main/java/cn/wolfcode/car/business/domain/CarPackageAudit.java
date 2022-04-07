package cn.wolfcode.car.business.domain;

import cn.wolfcode.car.base.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class CarPackageAudit {
    public static final Integer STATUS_IN_ROGRESS = 0;//审核中
    public static final Integer STATUS_REJECT = 1;//审核拒绝
    public static final Integer STATUS_PASS = 2;//审核通过
    public static final Integer STATUS_CANCEL = 3;//审核撤销
    private static final long serialVersionUID = 1L;


    private Long id;

    private Long serviceItemId;             //服务单项id
    private String serviceItemInfo;         //服务单项备注
    private BigDecimal serviceItemPrice;    //服务单项审核价格

    private ServiceItem serviceItem;        //服务单项对象

    private String instanceId;              //流程实例id

    private String creator;                 //创建者

    private Long auditorId;                 //当前审核人id
    private User auditor;                   //当前审核人对象

    private Long bpmnInfoId;                //关联流程id
    // private BpmnInfo bpmnInfo;              //关联流程定义对象

    private String info;                    //备注

    private Integer status = STATUS_IN_ROGRESS;     //状态【进行中0/审核拒绝1/审核通过2/审核撤销3】

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date auditTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

}