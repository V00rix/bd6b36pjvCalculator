package com.cvut.bd6b36pjv.io.api;

import com.cvut.bd6b36pjv.exceptions.WrongOperandException;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

import java.math.BigDecimal;

/**
 * API for console interactions
 */
public interface IOManager {
    /**
     * Prints message and parses input into operation
     * @return BasicCalculatorOperation to be computed
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    String readOperation() throws WrongOperationException, IllegalAccessException;

    /**
     * Reads two real number operands
     * @param op BasicCalculatorOperation to specify names for operands
     * @return Two real numbers
     * @throws WrongOperandException See {@see WrongOperandException}
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    BigDecimal[] readOperands(String op) throws WrongOperandException, WrongOperationException;

    /**
     * Reads two real number operands
     * @return Two real numbers
     * @throws WrongOperandException See {@see WrongOperandException}
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    BigDecimal[] readOperands() throws WrongOperandException, WrongOperationException;

    /**
     * Reads calculation precision
     * @return Precision
     * @throws WrongOperandException See {@see WrongOperationException}
     */
    int readPrecision() throws WrongOperandException;

    /**
     * Prints calculation string
     * @param operands Operands
     * @param op BasicCalculatorOperation
     * @param result Result of calculation
     * @param precision Precision of display (not calculation)
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    void printResult(BigDecimal[] operands, String op, BigDecimal result, int precision) throws WrongOperationException;

    /**
     * Prints error message
     * @param s error code
     */
    void errorMessage(String s);
}
