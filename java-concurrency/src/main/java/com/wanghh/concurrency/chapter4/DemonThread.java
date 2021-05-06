package com.wanghh.concurrency.chapter4;

/**
 * @author wanghh
 * @date 2021-04-25
 */
public class DemonThread {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "running");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "ending");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // t1设置为守护线程:main线程退出 t1线程也退出

        t1.setDaemon(true);

        t1.start();
        System.out.println(Thread.currentThread().getName());
    }
}
