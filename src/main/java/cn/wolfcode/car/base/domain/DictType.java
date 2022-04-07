package cn.wolfcode.car.base.domain;


import lombok.Getter;
import lombok.Setter;

/**
 * 字典类型(分类)
 */
@Setter
@Getter
public class DictType {
    private Long id;                //字典主键

    private String name;            //字典名称

    private String type;            //字典类型

    private char status = '0';      //状态（0正常 1停用）

    private String remark;          //备注
}