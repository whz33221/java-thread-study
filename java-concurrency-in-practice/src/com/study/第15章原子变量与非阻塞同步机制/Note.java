package com.study.第15章原子变量与非阻塞同步机制;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Note {
    /**
     * 一、为什么有原子变量和非阻塞同步机制
     *      1.独占锁同步方式（悲观锁），在低中竞争资源场景下，因为上下文切换，线程的挂起和恢复，线程的阻塞，
     *      浪费了大量的资源和降低了可伸缩性
     *      2.原子变量和非阻塞同步机制（乐观锁），因为该方式不存在资源竞争，可伸缩性更高，在低中
     *      竞争资源场景下，只有CAS执行失败，而重试的资源浪费，性能方面比锁同步方式高很多，而
     *      大多数允许环境不存在极其高的资源竞争，所以原子变量和非阻塞同步机制是更优的同步方式
     * 二、CAS
     *      1.JVM底层使用了底层硬件机器指令，进行读-改-写的原子操作
     *      成功返回修改值，失败返回当前值
     * 三、原子变量类
     *      1.特点
     *          1）本身带有volatile的可见性
     *          2）提供了CAS操作的API，使其修改具有原子性
     *          3）没有重写equals（）和hashCode（）方法，所以每个对象都是不等的，判断值
     *          需要调用get（）获取数值进行比较，所以不建议用原子变量类作为Map的key（因为
     *          key将永远唯一，不会出现key相同而替换）
     *      2.种类
     *          AtomicInteger、AtomicLong、AtomicBoolean、
     *          AtomicReference（作用：提供读-改-写 CAS操作）
     *          原子数据等等
     *      3.volatile与原子变量的选择
     *          1）原子变量是一种更好的volatile，功能更多，volatile变量则更轻量，根据需求
     *          进行灵活永远即可，性能在可比较的方面，基本相同
     *
     * 四、非阻塞算法
     *      1.非阻塞算法是指，算法中不存在任何位置将使当前上下文进入阻塞的状态
     *      2.在不用互斥锁的情况下解决并发执行环境的Race Condition。
     *      二者区别：非阻塞算法有可能存在互斥锁，比如：自旋锁（Spin-lock）算法：某一执行上下文在获得锁之后，其他上下文需要循环忙等
     * 五、原子域更新器
     *      1.是什么？
     *      它表示现有volatile域的一种基于反射的视图，从而可以在已有的volatile域上使用CAS
     *      2.为什么用它
     *      使用原子域更新器，可以代替原子变量，转而使用更轻量的volatile变量，在确保原子性需求的同时
     *      减少了分配原子变量对象所带来的资源消耗
     *      3.怎么用
     *      基本上很好用，当需要极限压榨出性能才需要，大多数情况下，原子变量性能已经很好了
     * 六、原子变量存在的问题
     *      1.都在存在ABA问题。
     *      解决方案：需要有需求解决ABA问题，可以使用AtomicStampedReference来解决AtomicMarkableReference
     *      2.AtomicStampedReference和AtomicMarkableReference的区别
     *      AtomicStampedReference中每个pair保存（引用，时间戳），通过时间戳我们可以指代引用被改了
     *      3.多少次
     *      AtomicMarkableReference中每个pair保存（引用，布尔值），通过布尔值，我们可以知道引用是否被改过
     *
     */

    public static void main(String[] args) {

    }
}
