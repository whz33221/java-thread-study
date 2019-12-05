package com.study.java并发编程艺术.第7章java中的13原子操作类.原子更新引用类型;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldUpdaterTeset {
    private final static AtomicReferenceFieldUpdater<User,Integer> arfu =
            AtomicReferenceFieldUpdater.newUpdater(User.class,Integer.class,"age");
    public static void main(String[] args) {
        User user = new User();
        user.setAge(10);
        Integer age = user.getAge();
        arfu.compareAndSet(user,age,100);
        System.out.println(user.getAge());
    }
    static class User{
        public  volatile String name;
        public volatile Integer age;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
