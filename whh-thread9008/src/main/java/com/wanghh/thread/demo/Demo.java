package com.wanghh.thread.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author wanghh
 * @date 2021-04-20
 */
public class Demo {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        /*天数差*/
        Date fromDate1 = simpleFormat.parse("2015-01-01");
        Date toDate1 = simpleFormat.parse("2021-04-20");
        long from1 = fromDate1.getTime();
        long to1 = toDate1.getTime();
        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
        System.out.println("两个时间之间的天数差为：" + days);

        int year = 2020;
        int i = LocalDate.of(year, 1, 1).lengthOfYear();
        System.out.println(i);
    }

}
