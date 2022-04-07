package cn.wolfcode.car.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 公告
 */
@Setter
@Getter
public class Notice {
    private Long id;                //公告ID

    private String title;           //公告标题

    private char type;              //公告类型（1通知 2公告）

    private String content;         //公告内容

    private char status = '0';      //公告状态（0正常 1关闭）

    private String createBy;        //创建者(用户名)

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;        //创建时间

    private String remark;          //备注

}