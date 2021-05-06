package com.wanghh.concurrency.chapter6;

/**
 * 执行线程执行结束  守护线程也会结束
 * @author wanghh
 * @date 2021-04-29
 */
public class ThreadService {

    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task){
        executeThread = new Thread(){
            @Override
            public void run(){
                // 创建一个守护线程
                Thread runner = new Thread(task);
                runner.setDaemon(true);

                runner.start();
                try {
                    // runner线程执行结束后 才会执行executeThread线程
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }

    public void shutdown(long mills){
        long begin = System.currentTimeMillis();
        while (!finished){
            if (System.currentTimeMillis() - begin >= mills){
                System.out.println("任务超时，任务结束");
                executeThread.interrupt();
                break;
            }
            try {
                executeThread.sleep(1L);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }
        finished = false;
    }
}
