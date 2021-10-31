package com.wanghh.thread.tvolatile;

import java.util.concurrent.*;

/**
 *
 * 验证volatile关键字:可见性
 **/
public class VolatileTest1 {

    private static int number = 0;
    public static void main(String[] args) {
        /*ExecutorService executorService = new ThreadPoolExecutor(2,5,3,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());*/
        //try {
            /*executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "while之前number = " + number);
                while (number == 0){
                    System.out.println(Thread.currentThread().getName() + "while之后number = " + number);
                }
            });

            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                number = 1;
                System.out.println(Thread.currentThread().getName() + "执行完毕,number = " + number);
            });*/
            /*new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "while之前number = " + number);
                while (number == 0){
                    System.out.println(Thread.currentThread().getName() + "while之后number = " + number);
                }
            },"线程1").start();

            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                number = 1;
                System.out.println(Thread.currentThread().getName() + "执行完毕,number = " + number);
            },"线程2").start();
        }catch (Exception e){
            e.printStackTrace();
        }*/
        new Thread(() -> {
            while (number == 0){

            }
        }).start();
        try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        number = 1;
        System.out.println(number);
    }
}
