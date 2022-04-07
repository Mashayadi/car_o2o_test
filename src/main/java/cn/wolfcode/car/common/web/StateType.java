package cn.wolfcode.car.common.web;

import lombok.Getter;

public enum StateType {
    /** 成功 */
    SUCCESS(0),
    /** 警告 */
    WARN(301),
    /** 错误 */
    ERROR(500);

    @Getter
    private final int value;

    private StateType (int value){
        this.value = value;
    }


}
