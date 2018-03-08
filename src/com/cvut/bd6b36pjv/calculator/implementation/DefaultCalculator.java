package com.cvut.bd6b36pjv.calculator.implementation;


import com.cvut.bd6b36pjv.calculator.api.ICalculator;
import com.cvut.bd6b36pjv.calculator.api.IReal;
import com.cvut.bd6b36pjv.calculator.domain.BasicCalculatorOperation;
import com.cvut.bd6b36pjv.calculator.domain.Real;
import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

public class DefaultCalculator implements ICalculator {

    private IReal[] operands;

    /**
     * Result container
     */
    private IReal result;

    public IReal getLastResult() {
        return this.result;
    }

    public DefaultCalculator() {
        this.result = new Real(0);
        this.operands = new Real[]{
                new Real(0),
                new Real(0)
        };
    }

    @Override
    public IReal compute(String op, IReal[] operands) throws WrongOperationException {
        switch (op) {
            case BasicCalculatorOperation.ADDITION:
                this.operands[0].setValue(this.result = operands[0].add(operands[1]));
                this.operands[1].setValue(0);
                return this.result;
            case BasicCalculatorOperation.SUBTRACTION:
                this.operands[0].setValue(this.result = operands[0].subtract(operands[1]));
                this.operands[1].setValue(0);
                return this.result;
            case BasicCalculatorOperation.MULTIPLICATION:
                this.operands[0].setValue(this.result = operands[0].multiplyBy(operands[1]));
                this.operands[1].setValue(0);
                return this.result;
            case BasicCalculatorOperation.DIVISION:
                if (Double.compare(operands[1].doubleValue(), 0.0) == 0) {
                    throw new DivisionByZeroException();
                }
                this.operands[0].setValue(this.result = operands[0].divideBy(operands[1]));
                this.operands[1].setValue(0);
                return this.result;
            default:
                throw new WrongOperationException();
        }
    }
}
