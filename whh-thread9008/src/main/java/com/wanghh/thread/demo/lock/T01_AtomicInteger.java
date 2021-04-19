package com.wanghh.thread.demo.lock;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JUC下的类AtomicInteger-自旋锁
 * @author wanghh
 * @date 2021-04-16
 */
public class T01_AtomicInteger {

    private static AtomicInteger m = new AtomicInteger(0); // 轻量级锁

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];

        CountDownLatch latch = new CountDownLatch(threads.length);

        // 加object锁
        //Object o = new Object();

        for (int i = 0; i < threads.length; i++){
            threads[i] = new Thread( () -> {
                    for (int j = 0; j < 10000; j++){
                        // 底层使用自旋锁 -实现方式CAS
                        m.incrementAndGet();
                    }
                    latch.countDown();
            });
        }

        Arrays.stream(threads).forEach(t -> t.start());

        latch.await();

        System.out.println(m);
    }
}
