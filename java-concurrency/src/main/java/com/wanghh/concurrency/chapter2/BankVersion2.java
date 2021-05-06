package com.wanghh.concurrency.chapter2;

/**
 * @author wanghh
 * @date 2021-04-22
 */
public class BankVersion2 {

    private final static int max = 50;

    public static void main(String[] args) {


        //
        TicketWindowRunnable t1 = new TicketWindowRunnable();

        // 使用Java8优化
        Runnable t2 = () -> {
            int index = 1;
            while (index <= max){
                System.out.println(Thread.currentThread().getName() + "当前的号码是：" + (index++));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread wt1 = new Thread(t2,"一号窗口");
        Thread wt2 = new Thread(t2,"二号窗口");
        Thread wt3 = new Thread(t2,"三号窗口");

        wt1.start();
        wt2.start();
        wt3.start();
    }
}
