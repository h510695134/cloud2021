package com.wanghh.thread.race;

import com.sun.deploy.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 多线程模拟龟兔赛跑
 **/
public class Race implements Runnable{
    private static String winner;
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            // 模拟兔子睡觉
            if (Thread.currentThread().getName().equals("兔子") && i % 10 == 0){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean result = gameOver(i);
            if (result){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "-->跑了" + i + "步");
        }
    }

    // 判断比赛是否结束
    public boolean gameOver(int step){
        // 先判断是否产生胜利者
        if (null != winner){
            return true;
        }else {
            if (step >= 100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is " + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"乌龟").start();
        new Thread(race,"兔子").start();
    }
}
