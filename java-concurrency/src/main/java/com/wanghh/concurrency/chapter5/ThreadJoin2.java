package com.wanghh.concurrency.chapter5;

/**
 * 业务：三个线程 去三台服务器上采集数据，全部采集完成，保存到数据库
 * t1,t2,t3三个线程结束后 再执行main线程 使用join
 * @author wanghh
 * @date 2021-04-26
 */
public class ThreadJoin2 {

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunnable("M1", 10_000));
        Thread t2 = new Thread(new CaptureRunnable("M2", 15_000));
        Thread t3 = new Thread(new CaptureRunnable("M3", 30_000));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();


        long end = System.currentTimeMillis();
        System.out.println(String.format("保存开始时间：%s,结束时间：%s,耗时：%s",begin,end,(end-begin)));
    }
}

class CaptureRunnable implements Runnable{

    private String machineName;

    private long time;

    CaptureRunnable(String machineName,long time){
        this.machineName = machineName;
        this.time = time;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
            getResult();
            System.out.println(machineName + "采集结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 采集数据
     * @return 结果
     */
    public String getResult(){
        return machineName + "finished.";
    }
}
