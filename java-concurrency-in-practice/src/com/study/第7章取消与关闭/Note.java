package com.study.第7章取消与关闭;

import java.awt.*;
import java.time.*;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoUnit.MONTHS;
import static jdk.nashorn.internal.objects.NativeDate.now;

public class Note {
    /**
     * 一、取消与中断的选择
     *      自定义的取消机制无法与可阻塞的库函数实现良好的交互，所以最好就是用中断机制来代替
     *      取消机制
     * 二、中断机制
     *      1.策略
     *          1)直接外抛中断异常，给调用方处理
     *          2)延迟处理，交给执行代码处理，捕获到异常后（异常标记会被复位），
     *          记录中断标记，在finally中恢复中断标记
     *
     * 三、通过future实现取消
     *      1.良好的编程习惯: 对于在get阻塞过程中，抛出超时异常和中断异常的任务，而那你又不再需要
     *      获取结果的任务，应该及时调用cancel方法，取消任务及时释放资源。
     *      （可以在finally中调用，因为完成的任务不调用cancel也不会有问题）
     *      2.Future的cancel方法
     *      mayInterruptIfRunning设成false话，不允许在线程运行时中断，设成true的话就允许。
     * 四、处理不可中断的阻塞
     *      1.不可中断的阻塞分类
     *          1)同步socketIO
     *              通过关闭底层的套接字，达到中断阻塞的目的（会抛出SocketException异常）
     *          2)同步IO
     *              通过关闭Channel，达到中断阻塞的目的（会抛出AsynchronousCloseException）
     *          3)Select的异步IO
     *              调用close或wakeup会提前返回，抛出（ClosedSelectorException）
     *          4)获取锁时的阻塞
     *              使用显式锁进行阻塞
     *      2.处理中断的方式
     *          1）重写Thread的interrupt方法：继承Thread重写interrupt方法
     *          2）重写Future的cancel：采用newTaskFor来创建，封装重写了cancel方法的RunnableFuture
     * 五、线程池的终止
     *      1.shutdown：完成所有任务后关机
     *      2.shutdownNow：中断再执行任务，返回未执行任务
     *          如何使用：需要再在调用任务设计时，进行中断任务记录的逻辑设计，记录返回的未执行任务，
     *      通过捕捉中断异常，在任务结束时，判断终止状态，把被中断任务也记录下来（注意因代码设计
     *      造成被中断任务误报的情况）
     *      3.特殊方式：毒丸对象
     *          N个生成者提交了毒丸后将不再提交任务，消费者在接收N个毒丸对象后，终止服务
     * 六、处理非正常的线程终止
     *      1.通过调用时try-catch块捕捉throwable
     *      2.通过线程工厂，在每创建一个Thread时，设置一个UncaughtException
     *
     * 七、JVM的关闭
     *      1.关闭前，会调用所有的关闭钩子，在调用终结器，再直接关闭JVM(以直接抛弃的方式关闭
     *      守护线程)
     *          1）对于钩子
     *              1》添加方式：Runtime.addShutdownHook()
     *              2》设计原则：最好避免并行执行钩子，对钩子最好进行串行设计，避免活跃性问题
     *          2）对于终结器
     *              1》概念：对象（包括线程对象）再销毁时调用
     *              2》缺点因为回收机制，导致不能确定他什么时候执行，是否执行，且加大对象回收的
     *              性能消耗
     *              3》一般不使用
     *          3）守护线程
     *              设计原则：必须可以被安全抛弃---JVM再终止时不会对其关闭，而是直接关闭JVM，守护线程就同时直接被杀掉了，所以
     *              守护线程设计时，必须是可以被安全抛弃的
     *
     *      */

    public static void main(String[] args) {
//        LocalDate now = LocalDate.now();
//        Month of = Month.of(1);
//        OffsetTime now1 = OffsetTime.now(Clock.systemDefaultZone());

        LocalDateTime from = LocalDateTime.from(LocalDateTime.now());
        Instant.from(from);
        System.out.println(from);
    }
//        Future<Integer> future = Executors.newFixedThreadPool(1).submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//
//                return 2;
//            }
//        });
//        try {
//            Integer integer = future.get();
//            future.cancel(true);
//            Thread.sleep(1000);
//            System.out.println(integer);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
}
