package cn.wolfcode.car.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 岗位
 */
@Setter
@Getter
public class Post {
    private Long id;                    //岗位ID

    private String code;                //岗位编码

    private String name;                //岗位名称

    private Integer seq;                //显示顺序

    private String status = "0";        //状态（0正常 1停用）

    private String remark;              //备注

    private boolean flag = false;       //页面开关控制
}