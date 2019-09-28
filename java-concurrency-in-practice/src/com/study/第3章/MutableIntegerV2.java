package com.study.第3章;

/**
 * 优化地方：在set方法加上sync
 * 存在问题
 * 1.当多线程访问，会出现线程安全问题
 *      1）竞态条件-->对于非volatile类型的64位数值变量double/long，修改动作为非原子操作
 *      JVM会将其分解成两个32位操作，此时，在get的时候，有可能读到32位（修改前）+ 32位（修改后）
 *      对应的long类型值
 *
 */
public class MutableIntegerV2 {
    private long value;

    public long getValue() {
        return value;
    }

    public synchronized void setValue(long value) {
        this.value = value;
    }
}
