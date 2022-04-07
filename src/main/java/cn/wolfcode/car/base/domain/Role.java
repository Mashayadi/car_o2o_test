package cn.wolfcode.car.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色
 */
@Setter
@Getter
public class Role {
    private Long id;                                //角色ID

    private String name;                            //角色名称

    private String rkey;                            //角色权限字符串

    private Integer seq;                            //显示顺序

    private String dataScope;                       //数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）

    private String status = "0";                    //角色状态（0正常 1停用）

    private char delFlag='0';                       //删除标志（0代表存在 2代表删除）

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;                        //创建时间

    private String remark;                          //备注

    //菜单
    private List<Long> menuIds = new ArrayList<>(); //当前角色下拥有的菜单ids
    //部门
    private List<Long> deptIds = new ArrayList<>(); //当前角色下拥有的部门ids

    private boolean flag = false;                   //页面控制开关

}