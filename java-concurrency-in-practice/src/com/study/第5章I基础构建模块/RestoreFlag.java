package com.study.第5章I基础构建模块;

public class RestoreFlag implements Runnable {


    @Override
    public void run() {
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            System.out.println("中断了");

        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RestoreFlag());
        thread.start();
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        System.out.println();
    }
}
