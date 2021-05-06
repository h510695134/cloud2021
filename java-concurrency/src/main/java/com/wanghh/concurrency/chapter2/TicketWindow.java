package com.wanghh.concurrency.chapter2;

/**
 * 模拟银行柜台叫号器
 * @author wanghh
 * @date 2021-04-22
 */
public class TicketWindow extends Thread{

    private final String name;

    /**
     * 实例化一次  共享变量
     */
    private static final int max = 50;

    /**
     * 实例化一次  共享变量
     */
    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run(){
        while (index <= max){
            System.out.println("柜台名："+name + "，当前的号码是：" + (index++));
        }
    }
}
