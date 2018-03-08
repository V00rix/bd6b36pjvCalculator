package com.cvut.bd6b36pjv.calculator.api;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

public interface ICalculator {
    /**
     * Compute result given operation and operands
     * @param op BasicCalculatorOperation
     * @param operands Operands
     * @return Result of operation
     */
    IReal compute(String op, IReal[] operands) throws WrongOperationException;
}
