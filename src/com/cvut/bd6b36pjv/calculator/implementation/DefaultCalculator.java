package com.cvut.bd6b36pjv.calculator.implementation;


import com.cvut.bd6b36pjv.calculator.api.ICalculator;
import com.cvut.bd6b36pjv.calculator.domain.enumeration.BasicCalculatorOperation;
import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DefaultCalculator implements ICalculator {

    private BigDecimal[] operands;

    /**
     * Result container
     */
    private BigDecimal result;

    public BigDecimal getLastResult() {
        return this.result;
    }

    public DefaultCalculator() {
        this.result = new BigDecimal(0);
        this.operands = new BigDecimal[]{
                new BigDecimal(0),
                new BigDecimal(0)
        };
    }

    @Override
    public BigDecimal compute(String op, BigDecimal[] operands) throws WrongOperationException {
        switch (op) {
            case BasicCalculatorOperation.ADDITION:
                this.operands[0] = this.result = operands[0].add(operands[1]);
                this.operands[1] = new BigDecimal("0");
                return this.result;
            case BasicCalculatorOperation.SUBTRACTION:
                this.operands[0] = this.result = operands[0].subtract(operands[1]);
                this.operands[1] = new BigDecimal("0");
                return this.result;
            case BasicCalculatorOperation.MULTIPLICATION:
                this.operands[0] = this.result = operands[0].multiply(operands[1]);
                this.operands[1] = new BigDecimal("0");
                return this.result;
            case BasicCalculatorOperation.DIVISION:
                if (Double.compare(operands[1].doubleValue(), 0.0) == 0) {
                    throw new DivisionByZeroException();
                }
                this.operands[0] = this.result = operands[0].divide(operands[1], RoundingMode.HALF_UP);
                this.operands[1] = new BigDecimal("0");
                return this.result;
            default:
                throw new WrongOperationException();
        }
    }
}
