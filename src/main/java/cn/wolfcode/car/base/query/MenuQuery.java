package cn.wolfcode.car.base.query;

import cn.wolfcode.car.common.base.query.QueryObject;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;

/**
 * 系统菜单查询对象
 */
@Setter
@Getter
public class MenuQuery extends QueryObject {
    private String name;
    private Character visible;

    private Long roleId;

}
