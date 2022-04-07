package cn.wolfcode.car.base.domain;


import lombok.Getter;
import lombok.Setter;

/**
 * 系统配置
 */
@Setter
@Getter
public class Config {

    private Long id;            //配置id

    private String name;        //参数名称

    private String ckey;        //参数键名

    private String value;       //参数键值

    private char type;          //系统内置（Y是 N否）

    private String remark;      //参数备注

}