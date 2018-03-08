package com.cvut.bd6b36pjv.calculator.api;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

public interface IGuiCalculator extends ICalculator {
    IReal getCurrentOperand();
    IReal compute() throws WrongOperationException;
    public void setOperation(String op);
    void pushOperand();
}
