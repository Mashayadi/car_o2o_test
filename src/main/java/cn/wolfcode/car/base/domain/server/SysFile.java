package cn.wolfcode.car.base.domain.server;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统文件相关信息
 */
@Setter
@Getter
public class SysFile {
    private String dirName;             //盘符路径

    private String sysTypeName;         //盘符类型

    private String typeName;            //文件类型

    private String total;               //总大小

    private String free;                //剩余大小

    private String used;                //已经使用量

    private double usage;               //资源的使用率

}
