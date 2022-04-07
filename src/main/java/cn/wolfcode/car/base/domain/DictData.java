package cn.wolfcode.car.base.domain;


import lombok.Getter;
import lombok.Setter;

/**
 * 字典数据
 */
@Setter
@Getter
public class DictData {
    private Long id;                //字典编码

    private Integer seq;            //字典排序

    private String label;           //字典标签

    private String value;           //字典键值

    private String type;            //字典类型

    private String cssClass;        //样式属性（其他样式扩展）

    private String listClass;       //表格回显样式

    private char isDefault;         //是否默认（Y是 N否）

    private char status = '0';      //状态（0正常 1停用）

    private String remark;          //备注
}