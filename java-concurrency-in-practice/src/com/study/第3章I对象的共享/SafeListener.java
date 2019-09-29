package com.study.第3章I对象的共享;

/**
 * 安全示例
 * 满足了一下需求
 * 1.通过构造私有化，提供实例方法，确保实例在被客户端代码拿到之前，完成监听器的注册的同时
 * 不在构造方法内，进行注册动作。
 */
public class SafeListener {
//    private final EventListener listener;

//    private SafeListener() {
//        this.listener = new EventListener();
//    }
//
//    public static SafeListener newInstance(EventSource source){
//        SafeListener safe = new SafeListener();
//        source.registerListener(safe.listener);
//        return safe;
//    }

}
