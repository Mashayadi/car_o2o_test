package cn.wolfcode.car.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {

    public static final Integer STATUS_APPOINTMENT = 0; // 预约中
    public static final Integer STATUS_ARRIVAL = 1; // 到店
    public static final Integer STATUS_CANCEL = 2; // 取消
    public static final Integer STATUS_OVERTIME = 3; // 超时
    public static final Integer STATUS_SETTLE = 4; // 结算


    private Long id;

    /** 客户姓名 */
    private String customerName;

    /** 客户联系方式 */
    private Long customerPhone;

    /** 预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date appointmentTime;

    /** 实际到店时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date actualArrivalTime;

    /** 车牌号码 */
    private String licensePlate;

    /** 汽车类型 */
    private String carSeries;

    /** 服务类型【维修0/保养1】 */
    private Integer serviceType;

    /** 创建时间 */
    private Date createTime;

    /** 备注信息 */
    private String info;

    /** 状态【预约中0/已到店1/用户取消2/超时取消3】 */
    private Integer status = STATUS_APPOINTMENT;

}