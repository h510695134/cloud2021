package com.wanghh.thread.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-10-24 20:35
 **/
public class MapTest {

    public static void main(String[] args) {
        //Map<String,String> map = new HashMap<>();
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
                },String.valueOf(i)).start();
        }
    }
}
