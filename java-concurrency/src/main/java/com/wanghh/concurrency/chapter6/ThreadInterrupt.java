package com.wanghh.concurrency.chapter6;

/**
 * @author wanghh
 * @date 2021-04-27
 */
public class ThreadInterrupt {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true){

            }
        });
        t.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }

}
