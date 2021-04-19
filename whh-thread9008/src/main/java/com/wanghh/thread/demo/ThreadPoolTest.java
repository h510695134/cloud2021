package com.wanghh.thread.demo;

import com.wanghh.thread.demo.Task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wanghh
 * @date 2021-04-14
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //corePoolSize:核心线程数
        //maximumPoolSize:最大线程数
        //keepAliveTime：线程处于空闲状态，最多可以空闲多久
        //new ArrayBlockingQueue 阻塞队列 ：在任意时刻,不管并发有多高，永远只有一个线程进行队列的入队或者出队操作 线程安全的队列

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,60,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory());

        for (int i = 0; i < 9; i++){
            pool.execute(new Task(i));
        }

        pool.shutdown();
    }
}
