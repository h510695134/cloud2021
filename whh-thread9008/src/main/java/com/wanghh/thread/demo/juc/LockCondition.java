package com.wanghh.thread.demo.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanghh
 * @date 2021-04-19
 */
public class LockCondition {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] ac = "ABCDEFG".toCharArray();

        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();// 队列
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                for (char c : aI) {
                    System.out.println(c);
                    condition2.signal(); // 唤醒
                    condition1.await(); // 等待
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
