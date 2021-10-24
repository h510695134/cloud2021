package com.wanghh.thread.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-10-24 19:50
 * 使用juc实现线程之间的通信 三个线程 A->B->C->A
 **/
public class A {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++){
                try {
                    data.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++){
                try {
                    data.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++){
                try {
                    data.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}

class Data{
    private int number = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    public void printA() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1){
                // 等待
                condition1.await();
            }
            // 等于1 执行业务
            System.out.println(Thread.currentThread().getName() + "->" + number);
            number = 2;
            // 唤醒线程B执行
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printB() throws InterruptedException {
        lock.lock();
        try {
            while (number != 2){
                // 等待
                condition2.await();
            }
            // 等于2 执行业务
            System.out.println(Thread.currentThread().getName() + "->" + number);
            number = 3;
            // 唤醒线程C执行
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC() throws InterruptedException {
        lock.lock();
        try {
            while (number != 3){
                // 等待
                condition3.await();
            }
            // 等于3 执行业务
            System.out.println(Thread.currentThread().getName() + "->" + number);
            number = 1;
            // 唤醒线程A执行
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
