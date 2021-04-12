package com.wanghh.thread.demo;

/**
 * @program: cloud2021
 * @description 多线程学习
 * @author: wanghh
 * @create: 2021-04-10 21:31
 **/
public class VolatileTest {

    /**
     * 多线程在执行时,访问共享变量  会在每个线程内创建一个变量副本
     * 虽然是共享变量 但是在每个线程工作内存中都会创建一个共享变量副本
     *
     * 所以在修饰共享变量需要加volatile关键字
     * volatile关键字用来保证多线程操作共享变量的可见性 让数据实现一致性
     */
    private static volatile boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("waiting data.......");
            while (!initFlag){

            }
            System.out.println("====================success");
        }).start();

        // 主线程休眠
        Thread.sleep(2000);

        // 再开启一个线程
        new Thread(() -> prepareData()).start();
    }

    public static void prepareData(){
        System.out.println("prepare data");
        initFlag = !initFlag;
        System.out.println("prepare data end..........");
    }
}
