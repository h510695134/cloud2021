package com.wanghh.concurrency.chapter2.strategy;

import com.wanghh.concurrency.chapter2.strategy.CalculatorStrategy;

/**
 * @author wanghh
 * @date 2021-04-22
 */
public class SimpleCalculatorStrategy implements CalculatorStrategy {
    @Override
    public double calculate(double salary, double bonus) {
        return salary * 0.1 + bonus * 0.15;
    }
}
