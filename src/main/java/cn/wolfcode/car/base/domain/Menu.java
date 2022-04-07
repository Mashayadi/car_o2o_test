package cn.wolfcode.car.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 左侧系统菜单
 */
@Setter
@Getter
public class Menu {
    private Long id;                            //菜单ID

    private String name;                        //菜单名称

    private Long parentId;                      //父菜单ID

    private String parentName;                  //父菜单名称

    private Integer seq;                        //显示顺序

    private String url;                         //请求地址

    private String target;                      //打开方式（menuItem页签 menuBlank新窗口）

    private char type;                          //菜单类型（M目录 C菜单 F按钮）

    private char visible;                       //菜单状态（0显示 1隐藏）

    private char refresh;                       //是否刷新（0刷新 1不刷新）

    private String perms;                       //权限标识

    private String icon;                        //菜单图标

    private String remark;                      //备注

    List<Menu> children = new ArrayList<>();    //子菜单
}