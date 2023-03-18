package com.wanghh.thread.juc;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2022-02-05 20:04
 **/
public class ThreadDemo extends Thread{


    public ThreadDemo(){}

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("thread-name:" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ThreadDemo thread01 = new ThreadDemo();
        ThreadDemo thread02 = new ThreadDemo();
        ThreadDemo thread03 = new ThreadDemo();
        thread01.start();
        thread02.start();
        thread03.start();
    }

}
