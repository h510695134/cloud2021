package com.wanghh.thread.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-10-24 20:09
 * 多个线程操纵arraylist 会造成并发修改异常的错误
 **/
public class TestArrayList {

    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,10));
                System.out.println(list);
            }).start();
        }
    }
}
