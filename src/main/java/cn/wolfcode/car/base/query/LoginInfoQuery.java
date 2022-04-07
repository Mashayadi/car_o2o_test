package cn.wolfcode.car.base.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 登录日志查询对象
 */
@Setter
@Getter
public class LoginInfoQuery extends QueryObject {
    private String ipaddr;
    private String loginName;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}
