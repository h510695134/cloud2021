package com.wanghh.thread.proxy;

/**
 *静态代理总结:
 *  代理对象和真实对象都要实现同一个接口
 *  代理对象要代理真实角色
 **/
public class StaticProxy {
    public static void main(String[] args) {
        Marry weddingCompany = new WeddingCompany(new You());
        weddingCompany.happyMarry();
        new Thread()
    }
}

interface Marry{
    void happyMarry();
}

class You implements Marry{

    @Override
    public void happyMarry() {
        System.out.println("我要结婚了!");
    }
}

class WeddingCompany implements Marry{

    private Marry marry;
    public WeddingCompany(Marry marry){
        this.marry = marry;
    }
    @Override
    public void happyMarry() {
        before();
        marry.happyMarry();
        after();
    }

    private void after() {
        System.out.println("结婚之后,收拾会场");
    }

    private void before() {
        System.out.println("结婚之前,布置会场");
    }
}

