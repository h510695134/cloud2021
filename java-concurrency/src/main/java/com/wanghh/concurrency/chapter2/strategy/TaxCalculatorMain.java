package com.wanghh.concurrency.chapter2.strategy;

import com.wanghh.concurrency.chapter2.TaxCalculator;
import com.wanghh.concurrency.chapter2.strategy.CalculatorStrategy;
import com.wanghh.concurrency.chapter2.strategy.SimpleCalculatorStrategy;

/**
 * @author wanghh
 * @date 2021-04-22
 */
public class TaxCalculatorMain {

    public static void main(String[] args) {
        /*TaxCalculator taxCalculator = new TaxCalculator(10000, 200){
            @Override
            public double calcTax(){
                return getBonus() + getSalary();
            }
        };

        double calcTax = taxCalculator.calculate();

        System.out.println(calcTax);*/

        // 使用策略模式优化代码
        CalculatorStrategy strategy = new SimpleCalculatorStrategy();
        TaxCalculator taxCalculator = new TaxCalculator(10000, 200,strategy);

        System.out.println(taxCalculator.calculate());
    }
}
