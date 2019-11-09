package com.study.第16章内存模型;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Note {
    /**
     * 一、平台的内存模型
     *      1.每个cpu都会有高速缓存
     *      --》这将导致所有的线程在没有同步的情况下看到不同状态的变量
     *
     * 二、JVM的重排序
     *      1.JVM会对程序代码的进行重排序
     *      -->单线程中可以确保结果正确，在多线程中,两个线程之间的代码是按如何一种顺序执行
     *      将无法预测，因此会发生各种问题。
     *      2.JMM为程序所有操作定义了一个偏序关系，称为“Happens-Before”
     *      --》当两个线程执行的代码不满足偏序关系，就无法推测两个线程执行代码的次序，导致结果无法预测
     * 三、借助同步解决重排序
     *      1.利用同步，我们可以让线程在可见域有序的，可预测的执行，
     *
     * 四、发布
     *      1.不安全发布本质：在发布一个共享对象与另一个线程访问该对象之间，缺少一种Happens-Before
     *      的排序
     *      --》安全发布：发布对象必须在其他线程访问对象之前执行
     *      2.安全发布的正确姿势
     *          1》提前初始化：通过类的静态变量new
     *          --》JVM加载类时进行初始化，此过程非守护线程无法访问，最终保证安全发布
     *          2》延迟初始化加载：
     *          错误做法：双重检查加锁--》在某一线程new的时候，另外线程有可能访问到其初始化一般的实例
     *          正确做法：延迟初始化占位类模式
     * 五、初始化过程中的安全性
     *          1.不可变对象：发布final域变量，只要正确的进行构造（即保证初始化过程的安全性，构造过程没有逸出对象-构造方法内/双重
     *          检查加锁形式的逸出），就可以做到在后续没有进行同步的同时，保证安全性
     *
     *
     *
     *
     *
     */
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(thread);
        }).start();
        LockSupport.park("三国");
        System.out.println("主线程结束");


    }
}
