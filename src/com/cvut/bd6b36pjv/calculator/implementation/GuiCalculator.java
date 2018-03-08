package com.cvut.bd6b36pjv.calculator.implementation;

import com.cvut.bd6b36pjv.calculator.api.IGuiCalculator;
import com.cvut.bd6b36pjv.calculator.api.IReal;
import com.cvut.bd6b36pjv.calculator.domain.CalculatorOperation;
import com.cvut.bd6b36pjv.calculator.domain.Real;
import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

public class GuiCalculator implements IGuiCalculator {
    private IReal[] operands;

    private IReal result;

    private String operation;

    public GuiCalculator() {
        this.result = new Real(0);
        this.operands = new Real[]{
                new Real(0),
                new Real(0)
        };
    }

    @Override
    public IReal compute(String op, IReal[] operands) throws WrongOperationException {
        if (op.equals(CalculatorOperation.NOTHING))
            return this.result;
        else {
            System.out.println(this.operands[1].doubleValue());
            System.out.println(this.operands[0].doubleValue());
            System.out.println(op);
            switch (op) {
                case CalculatorOperation.ADDITION:
                    this.operands[1].setValue(this.result = operands[0].add(operands[1]));
                    this.operands[0].setValue(0);
                    this.operation = CalculatorOperation.NOTHING;
                    return this.result;
                case CalculatorOperation.SUBTRACTION:
                    this.operands[1].setValue(this.result = operands[0].subtract(operands[1]));
                    this.operands[0].setValue(0);
                    this.operation = CalculatorOperation.NOTHING;
                    return this.result;
                case CalculatorOperation.MULTIPLICATION:
                    this.operands[1].setValue(this.result = operands[0].multiplyBy(operands[1]));
                    this.operands[0].setValue(0);
                    this.operation = CalculatorOperation.NOTHING;
                    return this.result;
                case CalculatorOperation.DIVISION:
                    if (Double.compare(operands[1].doubleValue(), 0.0) == 0) {
                        throw new DivisionByZeroException();
                    }
                    this.operands[1].setValue(this.result = operands[0].divideBy(operands[1]));
                    this.operands[0].setValue(0);
                    this.operation = CalculatorOperation.NOTHING;
                    return this.result;
                default:
                    throw new WrongOperationException();
            }
        }
    }

    @Override
    public IReal compute() throws WrongOperationException {
        return this.result = this.compute(this.operation, this.operands);
    }

    @Override
    public IReal getCurrentOperand() {
        return operands[0];
    }

    @Override
    public void setOperation(String op) {
        this.operation = op;
    }

    @Override
    public void pushOperand() {
        this.operands[1].setValue(this.operands[0].doubleValue());
        this.operands[0].setValue(0);
    }
}
