package com.wanghh.concurrency.chapter2.strategy;

/**
 * @author wanghh
 * @date 2021-04-22
 */
public interface CalculatorStrategy {

    /**
     * 计算月薪
     * @param salary 工资
     * @param bonus 奖金
     * @return 结果
     */
    double calculate(double salary,double bonus);
}
