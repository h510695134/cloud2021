package com.atguigu.springcloud.alibaba.demo;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wanghh
 * @date 2021-04-9
 */
public class IdGeneratorSnowflake {

    /**
     * 取值范围 0-31
     */
    private long workerId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);

    @PostConstruct
    public void init(){
        workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
    }

    public synchronized long snowflakeId(){
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        Snowflake snowflake = IdUtil.createSnowflake(1,1);
        System.out.println(snowflake.nextId());

        // 线程池
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
    }
    public void getIdBySnowflake(){
        //Executors.newFixedThreadPool(5);
    }
}
