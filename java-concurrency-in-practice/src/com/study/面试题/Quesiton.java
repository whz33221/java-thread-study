package com.study.面试题;

public class Quesiton {

    /**
     * 1.跟 Synchronized 相比，可重入锁 ReentrantLock 其实现原理有什么不同？
     *          1》实现手段不同：
     *              Synchronized是在JVM层面实现的
     *              ReentrantLock是在JDK(Java Development Kit)层面实现的
     *          2》实现组件
     *              Synchronized
     *                  锁对象：ObjectMonitor
     *                  阻塞队列：cxq，waitSet，EntryList
     *              ReentrantLock
     *                  锁对象：Sync（实现AQS）
     *                  阻塞队列：AQS
     *          3》锁的优化：
     *              Synchronized：
     *                  JVM编译是进行锁消除优化
     *                  自旋锁，偏向锁和锁膨胀的优化
     *              ReentrantLock：
     *                  自旋锁
     *          4》锁的实现功能不同：
     *              -锁竞争方面
     *              Synchronized只提供非公平锁的实现
     *              ReentrantLock提供非公平和公平锁的实现
     *
     *              -锁的获取方面
     *              Synchronized获取锁不可中断
     *              ReentrantLock获取锁提供可中断响应机制
     *
     *              -唤醒机制方面
     *              Synchronized唤醒机制要不唤醒一个，要不全部，
     *              ReentrantLock可以利用Condition分组进行唤醒挂起管理
     *
     *2.那么请谈谈 AQS 框架是怎么回事儿？
     *      1.全局来看：AQS是JDK提供的一个用于快速实现锁和同步器的一个组件。
     *      2.细节来看：AQS内部封装了维护独占锁和共享锁的线程阻塞队列的算法，客户可以实现其tryAcquired（），
     *      和tryReleased（）集合AQS内部的实现，快速设计自定义的一个锁或同步器
     *3.ReentrantLock 是如何实现可重入性的？
     *      其中的AQS会在exclusiveOwnerThread中记录当前获取锁的线程，每当该线程获取锁时，AQS的
     *  state变量就会+1，每当该线程释放锁时，state就会-1.
     *
     *4.除了 ReetrantLock，你还接触过 JUC 中的哪些并发工具？
     *      1>CountDownLatch-闭锁
     *      2>CyclicBarrier-栅栏
     *      3>ReadWriteLock-读写锁
     *      4>Semaphore-信号量
     *
     *
     *6.什么是 Java 的内存模型？
     *          java内存模型是java用于屏蔽不同操作系统平台底层内存操作的一个java自己定义的抽象内存模型，
     *      通过java内存模型，我们无需考虑不同操作系统平台操作内存的差异性。
     *
     *7.Java中各个线程是怎么彼此看到对方的变量的？
     *          java内存模型为共享内存模型，正常情况，线程无法看到对方工作内存中变量的改变，
     *      我们可以通过对变量进行标识volatile关键字，强制所有线程针对该变量在读和写的时候，都只操作于
     *      共享内存，这样，该变量的任何改变动作都会被所有线程锁察觉，达到线程间看到彼此变量的效果
     *
     * 8.什么是锁消除和锁粗化？
     *      锁消除：
     *          在jdk1.6后，JVM在JIT编译的时候，会针对上下文进行分析，去除不可能存在资源竞争的锁
     *      （比如针对局部变量的加锁）。
     *          其中逃逸分析为实现涉及的关键技术，根据逃逸分析，我们可以知道该变量的作用域返回是否存在
     *       线程共享，以此判断该资源是否存在线程间的竞争，从而决定是否进行锁消除
     *
     *      锁粗化：
     *          虚拟机JIT编译时如果遇到一连串对同一个锁进行获取与释放，那么就会把这一连串获取释放锁
     *       动作整合为一次请求
     *
     *
     * 9.如何让 Java 的线程彼此同步？你了解过哪些同步器？请分别介绍下。
     *      1.synchronize在JVM层面实现，为可重入非公平锁，1.6后具有偏向锁，自旋锁，锁消除，锁粗化等优化
     *      2.ReentrantLock在JDK层面实现的，为可重入，提供公平与非公平，相比synchronize多了些高级功能
     *      比如获取锁的中断机制，分组唤醒锁机制。
     *
     *
     *
     *
     *
     * 10.Synchronized 用过吗，其原理是什么？
     *          Synchronized，是在JVM层面实现，核心实现为ObjectMonitor对象监视器，其作为锁的同时，记录持有锁
     *      的线程，其中还维护了cxq，entryList，waitSet。
     *      》锁优化原理：
     *          无竞争时：为偏置锁
     *          少量竞争：进入轻量锁（竞争线程以自旋方式获取锁）
     *          大量竞争：此时所膨胀为重量级锁（自旋方式超过了设置等待极限，就会失败，导致锁膨胀）
     *      》膨胀：
     *          创建ObjectMonitor对象，锁的状态信息markword拷贝进其中，_owner指向持有锁线程
     *          cxq，entryList，waitSet三个队列
     *      》重量级锁：
     *          cxq：获取锁失败会进入
     *          waitSet：被wait的线程会进入
     *          entryList：锁出队时，有的策略会把cxq的线程合并至entryList，waitSet中被唤醒的线程也会进入
     *
     *
     *
     *
     *
     *
     * 10你刚才提到获取对象的锁，这个“锁”到底是什么？如何确定对象的锁？
     *      Synchronized中指ObjectMonitor，默认的话，静态方法为class对象的，默认方法为this，指定的为指定对象的
     *
     * 11.什么是可重入性，为什么说 Synchronized 是可重入锁？
     *      在Synchronized实现中，持有锁的线程可以重复获取锁，锁对象中的ObjectMonitor中的_recursion会记录持锁对象获取
     *    锁的次数。
     *
     *
     * 12.JVM 对 Java 的原生锁做了哪些优化？
     *      1》锁消除
     *      2》锁粗化
     *      3》偏向锁  markword直接记录线程Id
     *      4》轻量级锁  markword指向持有线程，自旋成功修改markword代表成功竞争到轻量级锁，自旋超过次数上限，就会视为
     *      失败导致锁膨胀
     *      5》自旋锁
     *
     * 13.为什么说 Synchronized 是非公平锁？
     *
     *
     *
     *
     * 14.请谈谈 volatile 有什么特点，为什么它能保证变量对所有线程的可见性？
     *      volatile：保证可见性，有序性，实现使用到的技术：内存膨胀，缓存锁
     *      可见性：因为被volatile标识的变量，每当被修改时，写线程都会回写共享内存，通过让其他线程的缓存无效（缓存锁机制）
     *
     *
     *
     * 15.请对比下 volatile 对比 Synchronized 的异同?
     *      volatile不能保证原子性
     *
     * 16.Java 中的线程池是如何实现的？
     *
     * 17.创建线程池的几个核心构造参数？
     *      核心线程数据
     *      最大线程数据
     *      空闲时长
     *      阻塞队列
     *      线程工厂
     * 18.线程池中的线程是怎么创建的？
     *      通过线程工厂构建，构造时没有传入，就使用默认的
     *
     * 19.如何在 Java 线程池中提交线程？
     *      submit（）
     *      execute（）
     *
     *
     *
     *
     *
     * CyclicBarrier 和 CountDownLatch 看起来很相似，请对比下呢？
     * 很多人都说要慎用 ThreadLocal，谈谈你的理解，使用 ThreadLocal 需要注意些什么？
     * 请谈谈 ThreadLocal 是怎么解决并发安全的？
     * 为什么说 Synchronized 是一个悲观锁？乐观锁的实现原理又是什么？什么是
     * 乐观锁一定就是好的吗?
     * 请谈谈 ReadWriteLock 和 StampedLock。
     */
}
