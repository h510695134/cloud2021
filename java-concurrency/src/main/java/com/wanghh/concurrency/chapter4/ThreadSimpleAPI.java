package com.wanghh.concurrency.chapter4;

import java.util.Optional;

/**
 * @author wanghh
 * @date 2021-04-26
 */
public class ThreadSimpleAPI {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Optional.of("hello").ifPresent(System.out::println);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        Optional.of("线程名:" + t1.getName()).ifPresent(System.out::println);
        Optional.of("线程ID：" + t1.getId()).ifPresent(System.out::println);
        // 线程优先级
        Optional.of("线程优先级：" + t1.getPriority()).ifPresent(System.out::println);
        t1.start();
    }
}
