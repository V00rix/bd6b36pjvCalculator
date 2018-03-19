package com.cvut.bd6b36pjv.calculator.api;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

import java.math.BigDecimal;

/**
 * Basic calculator API
 */
public interface ICalculator {
    /**
     * Compute result given operation and operands
     * @param op BasicCalculatorOperation
     * @param operands Operands
     * @return Result of operation
     */
    BigDecimal compute(String op, BigDecimal[] operands) throws WrongOperationException;
}
