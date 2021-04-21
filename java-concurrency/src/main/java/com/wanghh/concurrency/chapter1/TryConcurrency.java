package com.wanghh.concurrency.chapter1;

/**
 * @author wanghh
 * @date 2021-04-21
 */
public class TryConcurrency {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> redFromDataBase(),"readThread");
        Thread t2 = new Thread(() -> writeDataToFile(),"writeThread");

        t1.start();
        t2.start();
        /*redFromDataBase();
        writeDataToFile();*/
    }

    /**
     * 从数据库读数据
     */
    private static void redFromDataBase(){
        // read data from database and handle it.
        try {
            println("Begin read data from db.");
            Thread.sleep(1000*10L);
            println("Read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    /**
     * 往磁盘写数据
     */
    private static  void writeDataToFile(){
        try {
            println("Begin write data to file.");
            Thread.sleep(1000*10L);
            println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }
    private static void println(String message){
        System.out.println(message);
    }
}
