package com.study.java并发编程实战.第5章I基础构建模块;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private final static Semaphore sem = new Semaphore(2);
    private final static CountDownLatch count = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        count.await();
                        synchronized (sem) {
                            sem.acquire();
                            System.out.println("获取信号量，执行任务,剩余"+sem.getQueueLength());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        sem.release();
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
       count.countDown();
    }

}
