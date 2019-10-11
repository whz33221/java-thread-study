package com.study.第5章I基础构建模块;

import java.util.Collections;

public class Note {
    /**
     * 基础构建模块
     *
     * 一、同步容器类 Vector,HashTable,Collections.synchronizedCollection()，contains（）
     * 1.使用注意的点：
     *      1）他只是类线程安全，对于客户端代码应用代码部分，需要根据具体情况，判断在整个程序中
     *      是否线程安全
     *      2）容器的迭代都会存在线程问题，另外要注意隐形遍历，比如toString（），addALL（）等API
     * 2.并发容器类 CopyOnWriteArrayList，ConcurrentHashMap并发包中各种容器类
     *      1)并发容器类的与非线程安全容器类的对应关系
     *      Map - ConcurrentMap
     *      List - CopyOnWriteArrayList(侧重于遍历线程安全)
     *      LinkList/Queue - ConcurrentLinkedQueue（不会阻塞）
     *                     - BlockingQueue（会阻塞）
     *      SortedMap - ConcurrentSkipListMap(侧重于key的排序)
     *      SortedSet - ConcurrentSkipListSet（元素排序）
     *      2）功能细节
     *      ConcurrentMap:
     *          -核心实现逻辑：分段锁，Map非线程独占
     *          -优势：通过分段锁，除了下方API，其他都是线程安全，且性能消耗小
     *          -劣势：1>size、isEmpty只是一个估计值
     *                 2>原子操作无法通过锁扩展，但是服务端代码已常用提供方法
     *      CopyOnWriteArrayList
     *          -核心实现逻辑：写的时候，会拷贝副本，然后再作替换
     *          -优势：修改时通过改变引用至副本，改前的对象无改变，意味着迭代器引用的list不变
     *                 遍历时不存在线程安全问题
     *          -劣势：对于写非常频繁的场景，性能会非常低
     * 3.并发容器的应用设计模式
     *      1）阻塞队列 生产者-消费者
     *      2）串行线程封闭：
     *          通过利用并发容器，管理对象，使对象在线程与线程间传递，并且由始至终
     *      都只在一个线程中运行
     *      3）双端队列与工作密取
     *          相比1，他没有中间的任务队列，每个消费者都有自己的队列，然后再如果自己的任务消费
     *      完，他就会到其它消费者队列的尾端获取任务执行，相比1，极大减少数据竞争的发生频率。
     * 4.处理中断异常方法
     *      1）传递（抛异常）
     *      2）恢复中断状态（调用interrupt）
     *      当抛出Interrupt中断异常后，线程的标记会马上被中断标识重置为false，所以当不抛出异常时，
     *      我们至少要把Interrupt中断状态重新设置回true，方便我们后续排查，或者让后续代码知道之前逻辑被
     *      中断过，然后进行相应的处理
     * 5.同步工具类
     *      1）闭锁：在闭锁结束之前，门会一直关闭，并且没有任何线程能通过，一旦打开，将不能被重置
     *      2）FutureTask也可以做闭锁：在结果返回之前，一直阻塞（适用于单线程场景）
     *      3）栅栏：可以重复使用的闭锁
     *      4）信号量：用来控制同时访问某个特定资源的操作数量
     *
     * 本章所得
     * 1.线程安全概念层级
     *      1）类中线程安全
     *      2）整个程序中的线程安全（类以外）
     *
     */
    public static void main(String[] args) {

    }
}
