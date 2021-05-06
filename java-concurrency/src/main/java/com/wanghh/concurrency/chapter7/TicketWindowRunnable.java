package com.wanghh.concurrency.chapter7;

import sun.jvm.hotspot.oops.BranchData;

/**
 * @author wanghh
 * @date 2021-04-29
 */
public class TicketWindowRunnable implements Runnable{

    private int index = 1;

    private final static int max = 500;

    private final Object monitor = new Object();

    @Override
    public void run() {
        while (true){
            synchronized (monitor){
                if (index > max){
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "的号码是：" + (index++));
            }
        }
    }
}
