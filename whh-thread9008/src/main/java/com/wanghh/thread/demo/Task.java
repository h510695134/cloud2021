package com.wanghh.thread.demo;

/**
 * @author wanghh
 * @date 2021-04-14
 */
public class Task implements Runnable{

    private int nov;

    public Task(int i){
        this.nov = i;
    }

    @Override
    public void run() {
        System.out.println(String.format("执行当前任务的线程是：%s",Thread.currentThread().getName()));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("我是任务："+nov+"我在执行。。。");
    }
}
