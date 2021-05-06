package com.wanghh.concurrency.chapter5;

import java.util.stream.IntStream;

/**
 * join作用：main线程 会等待t1,t2线程执行结束后才会执行main线程，而t1,t2线程则会交替执行
 * @author wanghh
 * @date 2021-04-26
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1,1000).forEach(i ->
                    System.out.println(Thread.currentThread().getName() + "->" + i));
        });

        Thread t2 = new Thread(() -> {
            IntStream.range(1,1000).forEach(i ->
                    System.out.println(Thread.currentThread().getName() + "->" + i));
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        IntStream.range(1,1000).forEach(i ->
                System.out.println(Thread.currentThread().getName() + "->" + i));

    }
}
