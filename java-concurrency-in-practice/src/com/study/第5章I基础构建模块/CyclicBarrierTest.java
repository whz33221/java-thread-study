package com.study.第5章I基础构建模块;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierTest {
    private final static CyclicBarrier barrier = new CyclicBarrier(11);


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        final AtomicInteger num =new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        int i1 = num.incrementAndGet();
                        System.out.println("完成任务,等待数："+i1);
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        barrier.await();
        System.out.println("全部到位");
    }
}
