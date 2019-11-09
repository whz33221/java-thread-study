package com.study.java并发编程实战.第13章I显式锁;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Note {
    /**
     * 1.Lock显式锁(如ReentrantLock可重入锁)与内置锁相比的优缺点
     *      1）缺点：
     *        1>显式锁必须自己释放锁，程序员有时会忘记释放锁，安全性没有内置锁高
     *      2）优点：
     *        1>提供独有的限时获取锁API-tryLock();
     *        2>提供独有的可中断获取锁API-lockInterruptibly
     *        3>用于实现非块结构的加锁
     *        4)显式锁提供两种公平性选择
     *        -非公平（随机获取锁）
     *        final ReentrantLock lock = new ReentrantLock(false);
     *        -公平（按入队获顺序取锁）
     *        final ReentrantLock lock = new ReentrantLock(true);
     *      3)其他
     *        1>jdk5.0性能上显式锁更好，但显式锁没有线程转储信息
     *        2>jdk6.0性能上，双方基本没有区别，也解决了显式锁线程转储信息的问题
     * 2.我们应该怎么去选择两者
     *      1）全局上看
     *          1>内置锁编码更紧凑，而且属于JVM的内置属性，JVM可以针对内置锁做锁粒度优化，消除
     *          无效锁等等的优化，后续如果有性能优化，优先级也会比显式锁高
     *          2>相比之下，显式锁安全性更低，代码更零散，性能无差，只是提供了一些独有的功能实现
     *      综上所述：
     *          我们应该在内置锁无法满足需求的时候，在使用显式锁
     *3.读写锁（ReadWriteLock）
     *      1）提供两种公平性
     *          1>公平性：按先进先出
     *          2>非公平性：写锁无条件插队
     *      2）降级与升级
     *          1>大多数情况下，读写锁都不支持读锁升级，只支持写锁降级
 *          PS：锁降级的目的：当线程A获取了写锁，完成了修改操作，然后因为为了性能，它没有降级
 *          获取读锁就急于释放了写锁，在去获取读锁，想输出刚才新写入的数据，在这之前，如果有
 *          线程B获取了写锁，并修改了共享域，那么此时，线程A就没法输出到自己写入的数据了，
 *          所以在释放写锁之前，先获取读锁降级，再释放写锁，可以提高吞吐量的同时，保证写
 *          线程可以在释放写锁之后，获取到读锁，对自己的操作数据进行读操作
     *      3）具有可重入性
     *
     *
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.writeLock().lock();
                System.out.println("持有写锁线程数：" + lock.writeLock().getHoldCount());
                lock.readLock().lock();
                System.out.println("降级后持有写锁线程数：" + lock.writeLock().getHoldCount());
                while(true){
                    System.out.println("生成数据"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                lock.readLock().unlock();
//                System.out.println("释放读锁后持有写锁线程数：" + lock.writeLock().getHoldCount());
//                lock.writeLock().unlock();
//                System.out.println("释放写锁后持有写锁线程数：" + lock.writeLock().getHoldCount());
//                lock.readLock().lock();
//
//                lock.writeLock().lock();
//                System.out.println("升级后持有写锁线程数：" + lock.writeLock().getHoldCount());
//                lock.writeLock().unlock();
//                System.out.println("释放写锁后持有写锁线程数：" + lock.writeLock().getHoldCount());
            }
        }).start();

        while(true){
            lock.readLock().lock();
            System.out.println("主线程输出");
            lock.readLock().unlock();
        }
    }



}
