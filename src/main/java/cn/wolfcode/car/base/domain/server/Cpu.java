package cn.wolfcode.car.base.domain.server;


import cn.wolfcode.car.common.util.Arith;
import lombok.Setter;

/**
 * CPU相关信息
 */
@Setter
public class Cpu {
    private int cpuNum;             //核心数

    private double total;           //CPU总的使用率

    private double sys;             //CPU系统使用率

    private double used;            //CPU用户使用率

    private double wait;            //CPU当前等待率

    private double free;            //CPU当前空闲率

    public int getCpuNum() {
        return cpuNum;
    }

    public double getTotal() {
        return Arith.round(Arith.mul(total, 100), 2);
    }



    public double getSys() {
        return Arith.round(Arith.mul(sys / total, 100), 2);
    }


    public double getUsed() {
        return Arith.round(Arith.mul(used / total, 100), 2);
    }



    public double getWait() {
        return Arith.round(Arith.mul(wait / total, 100), 2);
    }



    public double getFree() {
        return Arith.round(Arith.mul(free / total, 100), 2);
    }


}
