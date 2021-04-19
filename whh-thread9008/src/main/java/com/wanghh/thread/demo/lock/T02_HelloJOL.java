package com.wanghh.thread.demo.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wanghh
 * @date 2021-04-16
 */
public class T02_HelloJOL {

    public static void main(String[] args) {
        Object o = new Object();

        // 加锁前  对象二进制结构
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        // 加锁后  对象二进制结构
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
