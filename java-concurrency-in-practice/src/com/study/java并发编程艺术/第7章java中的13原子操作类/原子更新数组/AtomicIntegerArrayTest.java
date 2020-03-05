package com.study.java并发编程艺术.第7章java中的13原子操作类.原子更新数组;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    /**
     * AtomicIntegerArray: 原子更新整型数组里的元素,就是一个元素全都为AtomicInteger的数组
     * 相似类：
     * AtomicLongArray
     * AtomicReferenceArray
     * AtomicIntegerArray
     *
     */
    static int[] value =  new int[] {1,2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);
    public static void main(String[] args) {
        int i = ai.addAndGet(0, 100);
        int andSet = ai.getAndSet(1,100);
        System.out.println(i);
        System.out.println(ai);
    }


}
