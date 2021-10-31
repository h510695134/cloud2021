package com.wanghh.thread.demo.lock;



import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * @author wanghh
 * @date 2021-04-16
 */
public class TestSynchronize {

    private static int m = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];

        CountDownLatch latch = new CountDownLatch(threads.length);

        // 加object锁
        Object o = new Object();

        for (int i = 0; i < threads.length; i++){
            threads[i] = new Thread( () -> {
                synchronized (o){
                    for (int j = 0; j < 10000; j++){
                        m++;
                    }
                    latch.countDown();
                }
            });
        }

        Arrays.stream(threads).forEach(t -> t.start());

        latch.await();

        System.out.println(m);
    }
}
