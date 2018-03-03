package com.cvut.bd6b36pjv.calculator;

public interface Calculator {
    /**
     * Get latest operation's result
     * @return result
     */
    double getLastResult();

    /**
     * Compute result given operation and operands
     * @param operation Operation
     * @param operands Operands
     * @return Result of operation
     */
    double compute(Operation operation, double[] operands);
}
