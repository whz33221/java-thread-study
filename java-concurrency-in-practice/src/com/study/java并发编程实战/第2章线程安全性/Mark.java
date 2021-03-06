package com.study.java并发编程实战.第2章线程安全性;

public class Mark {
    /**
     * 1.当对象变量存在共享与可变，那么就有可能存在线程安全问题
     * 2.解决线程问题的方式
     *      1）原子变量
     *      2）volatile
     *      3）显式锁
     *      3）内置锁（sycn）
     * 3.什么是竞态条件？
     *      由于不恰当的执行时序而出现不正确结果的这现象称为---竞态条件
     * 4.竞态条件的本质：
     *      基于一种可能失效的观察结果，来做出判断或者执行某个计算
     * 5.什么是复合操作？
     *      一组必须以原子方式执行才能在多线程环境下正确执行的操作
     *
     *
     *
     * 本章总结
     * 1.如何识别变量是否存在线程安全问题？
     * 2.解决线程安全问题的常用手段有哪些？
     * 概念：竞态条件，复合操作
     *
     */
}
