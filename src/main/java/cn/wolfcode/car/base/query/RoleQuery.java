package cn.wolfcode.car.base.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 角色查询对象
 */
@Setter
@Getter
public class RoleQuery extends QueryObject {

    private String name;
    private String rkey;
    private Character status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}
