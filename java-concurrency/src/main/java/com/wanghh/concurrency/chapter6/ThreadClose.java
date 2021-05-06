package com.wanghh.concurrency.chapter6;

/**
 * @author wanghh
 * @date 2021-04-27
 */
public class ThreadClose {

    private static class Worker extends Thread{

        private volatile boolean start = true;
        @Override
        public void run(){
            while (start){
                System.out.println("执行线程任务");
            }
        }

        public void shutdown(){
            this.start = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.shutdown();
    }
}
