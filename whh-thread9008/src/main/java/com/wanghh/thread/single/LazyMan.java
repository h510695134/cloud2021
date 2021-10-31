package com.wanghh.thread.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-10-31 21:32
 **/
public class LazyMan {

    private LazyMan(){
        synchronized (LazyMan.class){
            if (lazyMan != null){
                throw new RuntimeException();
            }
        }
    }

    /**
     * 1,分配内存空间
     * 2,执行构造方法,初始化对象
     * 3,把这个对象指向这个空间
     *
     * 创建一个对象分三步,会造成指令重排,所以要加volatile关键字
     */
    private volatile static LazyMan lazyMan;
    //双重检测锁模式  懒汉式单例模式 DCL懒汉式
    public static LazyMan getInstance(){
        if (lazyMan == null){
            synchronized (LazyMan.class){
                if (lazyMan == null){

                    lazyMan = new LazyMan(); // 不是一个原子操作

                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan.getInstance();
            }).start();
        }*/
        // 使用反射可以破坏单例模式
        LazyMan instance1 = LazyMan.getInstance();

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance2 = declaredConstructor.newInstance(null);

        System.out.println(instance1 == instance2);
    }
}
