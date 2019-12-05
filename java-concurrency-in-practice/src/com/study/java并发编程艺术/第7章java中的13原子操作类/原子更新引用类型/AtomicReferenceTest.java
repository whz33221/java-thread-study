package com.study.java并发编程艺术.第7章java中的13原子操作类.原子更新引用类型;

import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceTest   {
    /**
     * 相似类
     * AtomicReferenceFieldUpdaterTeset 针对对象的某一个字段，进行原子更新操作
     * AtomicMarkableReferencet 提供版本控制
     */
    static private AtomicReference ar = new AtomicReference<Date>();

    public static void main(String[] args) throws InterruptedException {
        ar.set(LocalTime.now());

        System.out.println(ar);
        Thread.sleep(1000);
        ar.compareAndSet(ar.get(),LocalTime.now());
        System.out.println(ar);
    }
}
