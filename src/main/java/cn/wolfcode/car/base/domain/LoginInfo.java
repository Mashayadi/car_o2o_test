package cn.wolfcode.car.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户登录日志
 */
@Setter
@Getter
public class LoginInfo {

    private Long id;                    //访问ID

    private String loginName;           //登录账号

    private String ipaddr;              //登录IP地址

    private String loginLocation;       //登录地点

    private String browser;             //浏览器类型

    private String os;                  //操作系统

    private String status;              //登录状态（0成功 1失败）

    private String msg;                 //提示消息

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;             //访问时间
}