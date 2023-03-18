package com.wanghh.thread.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-10-24 21:26
 **/
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start();
        Thread thread1 = new Thread(futureTask,"thread-01");
        Thread thread2 = new Thread(futureTask,"thread-02");
        Thread thread3 = new Thread(futureTask,"thread-03");
        thread1.start();
        thread2.start();
        thread3.start();
        /*Integer integer = futureTask.get();
        System.out.println(integer);*/
    }
}

class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println("thread-name:" + Thread.currentThread().getName());
        }
        return 1024;
    }
}
