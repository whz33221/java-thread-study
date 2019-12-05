package com.study.java并发编程艺术.第7章java中的13原子操作类.基本类型类;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicIntegerTest {
    private static AtomicInteger ai = new AtomicInteger(0);

       public static void main(String[] args) {
           /**
            *
            * -(jdk7-8)Unsafe只提供三种数据类型CAS操作：int long 引用类型，以外的数据类型，可以考虑，
            * 对外展示其他数据类型，内部转化为这三种类型，进行更新修改维护
            *
            * -相似类：
            * AtomicBoolean: 底层通过将Boolean转为int再进行更新操作
            * AtomicInteger
            * AtomicLong
            *
            */

           System.out.println(ai.addAndGet(1));
           System.out.println(ai.doubleValue());
           System.out.println((int)1.232442F);
    }
}
