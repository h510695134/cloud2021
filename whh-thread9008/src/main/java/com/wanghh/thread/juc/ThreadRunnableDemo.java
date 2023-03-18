package com.wanghh.thread.juc;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2022-02-05 20:16
 **/
public class ThreadRunnableDemo implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("thread-name:" + Thread.currentThread().getName());
        }
    }
}
class TestRunnable{
    public static void main(String[] args) throws InterruptedException {
        ThreadRunnableDemo threadRunnable = new ThreadRunnableDemo();
        Thread thread01 = new Thread(threadRunnable, "线程1");
        Thread thread02 = new Thread(threadRunnable, "线程2");
        Thread thread03 = new Thread(threadRunnable, "线程3");
        thread01.start();
        thread02.start();
        thread03.start();
        new Thread().join(100);
        new Object().wait(100);
        Thread.sleep(100);
    }
}
