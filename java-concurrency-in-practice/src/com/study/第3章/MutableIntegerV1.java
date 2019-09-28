package com.study.第3章;

/**
 * 存在问题
 * 1.当多线程访问，会出现线程安全问题
 *      1）可见性
 *      2）竞态条件
 */
public class MutableIntegerV1 {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
