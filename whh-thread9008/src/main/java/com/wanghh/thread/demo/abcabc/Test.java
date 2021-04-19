package com.wanghh.thread.demo.abcabc;

/**
 * 问题：用三个（N）个线程，按顺序输出ABC  ABC ABC
 * @author wanghh
 * @date 2021-04-19
 */
public class Test {

    public static void main(String[] args) {
        //三个线程 三个不同的队列condition a唤醒b b唤醒c
        new Thread(() -> {
            // 先输出 然后叫醒b
            System.out.println("A");
        },"Thread A").start();

        new Thread(() -> {
            // 先等待唤醒
            // 唤醒后输出
            System.out.println("B");
            // 然后叫醒c

        },"Thread B").start();

        new Thread(() -> {
            // 先等待唤醒
            // 唤醒后输出
            System.out.println("C");
            // 然后叫醒a
        },"Thread C").start();
    }
}
