package cn.wolfcode.car.base.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户查询对象
 */
@Setter
@Getter
public class UserQuery extends QueryObject {
    private Long roleId;
    private String loginName;
    private String phonenumber;

    private String status;

    private Long deptId;
}
