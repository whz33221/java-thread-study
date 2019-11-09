package com.study.java并发编程实战.第3章I对象的共享;

/**
        * 存在问题
        * 1.因为两个变量皆为共享资源且可变---线程安全？
        * --》不安全，存在问题，修改变量代码无同步
        *     1）重排：主线程可能会先true，在setNum-->导致读到num=0
        *     2）可见性：副线程可能无法察觉变量的改变-->导致副线程死循环
        */

public class NoVisibility {
    private static  boolean ready;
    private static int num;


    private static class ReadThread extends Thread{

        @Override
        public void run() {
            while(!ready){
                Thread.yield();
            }
            System.out.println("跳出了循环"+num);
        }
    }


    public static void main(String[] args) {
        ReadThread readThread = new ReadThread();
        readThread.start();
        num = 42;
        ready = true;

    }
}