package com.wanghh.thread.lambda;

/**
 * lambda表达式以及函数式接口
 *      lambda表达式相当于实现了接口中方法,然后再去调用方法
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2023-04-04 20:29
 **/
public class LambdaDemo {

    public static void main(String[] args) {
        // 相当于有一个子类 实现接口中的方法
        Printable p = (s) -> {
            System.out.println("lambda表达式,info=" + s);
            return s;
        };
        // 方法的调用
        String wanghh = p.printInfo("wanghh");
        System.out.println(wanghh);
    }
}
