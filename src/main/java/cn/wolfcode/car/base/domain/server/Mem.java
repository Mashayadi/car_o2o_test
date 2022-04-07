package cn.wolfcode.car.base.domain.server;


import cn.wolfcode.car.common.util.Arith;
import lombok.Setter;

/**
 * 內存相关信息
 */
@Setter
public class Mem {
    private double total;           //内存总量

    private double used;            //已用内存

    private double free;            //剩余内存

    public double getTotal() {
        return Arith.div(total, (1024 * 1024 * 1024), 2);
    }


    public double getUsed() {
        return Arith.div(used, (1024 * 1024 * 1024), 2);
    }


    public double getFree() {
        return Arith.div(free, (1024 * 1024 * 1024), 2);
    }


    public double getUsage() {
        return Arith.mul(Arith.div(used, total, 4), 100);
    }
}
