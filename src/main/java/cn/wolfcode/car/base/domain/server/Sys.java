package cn.wolfcode.car.base.domain.server;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统相关信息
 */
@Setter
@Getter
public class Sys {
    private String computerName;            //服务器名称

    private String computerIp;              //服务器Ip

    private String userDir;                 //项目路径

    private String osName;                  //操作系统

    private String osArch;                  //操作系统

}
