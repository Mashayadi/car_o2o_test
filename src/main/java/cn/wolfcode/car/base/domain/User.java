package cn.wolfcode.car.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户
 */
@Setter
@Getter
public class User {
    private Long id;                                        //用户ID

    private Long deptId;                                    //部门ID

    private Dept dept;                                      //部门对象

    private String loginName;                               //登录账号

    private String userName;                                //用户昵称

    private String userType;                                //用户类型（00系统用户 01注册用户）

    private String email;                                   //用户邮箱

    private String phonenumber;                             //手机号码

    private char sex;                                       //用户性别（0男 1女 2未知）

    private String avatar;                                  //头像路径

    private String password;                                //密码

    private String salt;                                    //盐加密

    private String status = "0";                            //帐号状态（0正常 1停用）

    private char delFlag = '0';                             //删除标志（0代表存在 2代表删除）

    private String loginIp;                                 //最后登录IP

    private Date loginDate;                                 //最后登录时间

    private String remark;                                  //备注

    private List<Long> roleIds = new ArrayList<>();         //当前用户拥有的角色ids

    private List<Long> postIds = new ArrayList<>();         //当前用户担任的岗位ids
}