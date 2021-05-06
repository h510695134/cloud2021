package com.wanghh.concurrency.chapter7;

/**
 * @author wanghh
 * @date 2021-04-29
 */
public class BankVersion {

    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();

        Thread t1 = new Thread(ticketWindow, "一号窗口");
        Thread t2 = new Thread(ticketWindow, "二号窗口");
        Thread t3 = new Thread(ticketWindow, "三号窗口");

        t1.start();
        t2.start();
        t3.start();
    }
}
