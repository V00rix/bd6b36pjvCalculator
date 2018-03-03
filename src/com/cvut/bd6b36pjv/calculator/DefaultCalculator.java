package com.cvut.bd6b36pjv.calculator;


public class DefaultCalculator implements Calculator {

    /**
     * Result container
     */
    private double result;

    @Override
    public double getLastResult() {
        return this.result;
    }

    @Override
    public double compute(Operation operation, double[] operands) {
        throw new UnsupportedOperationException();
    }

}
