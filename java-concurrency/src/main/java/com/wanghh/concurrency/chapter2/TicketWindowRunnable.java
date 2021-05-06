package com.wanghh.concurrency.chapter2;

/**
 * @author wanghh
 * @date 2021-04-22
 */
public class TicketWindowRunnable implements Runnable{

    /**
     * 实例化一次  共享变量
     */
    private final static int max = 50;

    /**
     * 实例化一次  共享变量
     */
    private int index = 1;

    @Override
    public void run() {
        while (index <= max){
            System.out.println(Thread.currentThread().getName() + "当前的号码是：" + (index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
