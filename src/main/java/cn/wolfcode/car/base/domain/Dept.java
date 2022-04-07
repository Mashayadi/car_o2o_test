package cn.wolfcode.car.base.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 部门
 */
@Setter
@Getter
public class Dept {
    private Long id;

    private Long parentId;          //父部门id

    private String parentName;      //上级部门name

    private String ancestors;       //祖级列表

    private String name;            //部门名称

    private Integer seq;            //显示顺序

    private String leader;          //负责人

    private String phone;           //联系电话

    private String email;           //邮箱

    private char status = '0';      //部门状态（0正常 1停用）

    private char delFlag= '0';      //删除标志（0代表存在 2代表删除）

}