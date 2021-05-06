package com.wanghh.concurrency.chapter2;

import com.wanghh.concurrency.chapter2.strategy.CalculatorStrategy;

/**
 * @author wanghh
 * @date 2021-04-22
 */
public class TaxCalculator {

    /**
     * 工资
     */
    private double salary;
    /**
     * 奖金
     */
    private double bonus;

    private CalculatorStrategy calculatorStrategy;

    public TaxCalculator(double salary, double bonus,CalculatorStrategy calculatorStrategy){
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    protected double calcTax(){
        return calculatorStrategy.calculate(salary,bonus);
    }

    public double calculate(){
        return calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

}
