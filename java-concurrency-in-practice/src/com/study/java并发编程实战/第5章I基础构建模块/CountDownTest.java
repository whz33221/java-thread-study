//package com.study.java并发编程实战.第5章I基础构建模块;
//
//import java.util.concurrent.CountDownLatch;
//
//public class CountDownTest {
//    private final static CountDownLatch start = new CountDownLatch(1);
//    private final static CountDownLatch finished = new CountDownLatch(10);
//
//    //1.要有工作线程和任务
//    //2.要有两个闭锁，起始门\结束门
//    public static void main(String[] args) throws Exception {
//        System.out.println(finished.getCount());
//        for (int i = 0; i < finished.getCount(); i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println("等待全部就位");
//                        start.await();
//                        System.out.println("开始执行任务");
//                        Thread.sleep(1000);
//                        synchronized (CountDownTest.class) {
//                            finished.countDown();
//                            System.out.println("剩余"+finished.getCount());
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//        Thread.sleep(2000);
//        start.countDown();
//        finished.await();
//        System.out.println("任务整体结束");
//    }
//}
