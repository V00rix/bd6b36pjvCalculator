package com.cvut.bd6b36pjv.calculator;


import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

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
    public double compute(Operation op, double[] operands) throws WrongOperationException {
        switch (op) {
            case ADDITION:
                return this.result = operands[0] + operands[1];
            case SUBTRACTION:
                return this.result = operands[0] - operands[1];
            case MULTIPLICATION:
                return this.result = operands[0] * operands[1];
            case DIVISION:
                if (Double.compare(operands[1], 0.0) == 0) {
                    throw new DivisionByZeroException();
                }
                return this.result = operands[0] / operands[1];
                default:
                    throw new WrongOperationException();
        }
    }
}
