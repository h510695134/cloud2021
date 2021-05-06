package com.wanghh.concurrency.chapter6;

/**
 * @author wanghh
 * @date 2021-04-29
 */
public class ThreadCloseForce {

    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();
        long begin = System.currentTimeMillis();
        // 指定执行任务 在指定时间内结束
        threadService.execute(() -> {
            while (true){

            }
        });
        // 任务执行10秒后 结束任务
        threadService.shutdown(10_000);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
