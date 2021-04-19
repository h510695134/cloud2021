package com.wanghh.thread.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanghh
 * @date 2021-04-19
 */
public class TestReentrantLock {

    private static volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock(true);

        lock.lock();

        // try finally 保证锁一定要释放
        try {
            i++;
        }finally {
            lock.unlock();
        }
        // 尝试是否可以上锁
        lock.tryLock();

    }
}
