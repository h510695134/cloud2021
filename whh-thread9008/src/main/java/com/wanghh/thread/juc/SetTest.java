package com.wanghh.thread.juc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-10-24 20:18
 **/
public class SetTest {

    public static void main(String[] args) {
        //Set<String> set = new HashSet<>();
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet();
        for (int i = 0; i <= 30;i++){
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,10));
                System.out.println(set);
            }).start();
        }
    }
}
