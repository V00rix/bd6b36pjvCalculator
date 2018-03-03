package com.cvut.bd6b36pjv.calculator;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

public interface Calculator {
    /**
     * Get latest operation's result
     * @return result
     */
    double getLastResult();

    /**
     * Compute result given operation and operands
     * @param op Operation
     * @param operands Operands
     * @return Result of operation
     */
    double compute(Operation op, double[] operands) throws WrongOperationException;
}
