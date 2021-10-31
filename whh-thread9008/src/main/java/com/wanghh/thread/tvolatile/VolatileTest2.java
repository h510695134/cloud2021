package com.wanghh.thread.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * volatile 不保证原子性
 * lock和synchronized 可以保证原子性
 * 在不使用lock和synchronized的前提下怎么保证原子性?
 *      使用原子类的包装类 AtomicInteger
 *      原子类的原理:使用CAS
 *      这些类的底层都直接和操作系统挂钩,在内存中修改值! Unsafe类是一个特殊的存在
 **/
public class VolatileTest2 {

    // volatile 不保证原子性
    //private volatile static int num = 0;

    private static AtomicInteger num = new AtomicInteger();

    private static void add(){
        //num++;
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        // 如果存活线程数大于2  证明上面的20个线程没有执行结束
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "  num = " + num);
    }
}
