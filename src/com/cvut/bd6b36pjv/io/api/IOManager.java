package com.cvut.bd6b36pjv.io.api;

import com.cvut.bd6b36pjv.calculator.api.IReal;
import com.cvut.bd6b36pjv.calculator.domain.BasicCalculatorOperation;
import com.cvut.bd6b36pjv.exceptions.WrongOperandException;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

/**
 * API for console interactions
 */
public interface IOManager {
    /**
     * Prints message and parses input into operation
     * @return BasicCalculatorOperation to be computed
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    String readOperation() throws WrongOperationException;

    /**
     * Reads two real number operands
     * @param op BasicCalculatorOperation to specify names for operands
     * @return Two real numbers
     * @throws WrongOperandException See {@see WrongOperandException}
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    IReal[] readOperands(String op) throws WrongOperandException, WrongOperationException;

    /**
     * Reads two real number operands
     * @return Two real numbers
     * @throws WrongOperandException See {@see WrongOperandException}
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    IReal[] readOperands() throws WrongOperandException, WrongOperationException;

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
    void printResult(IReal[] operands, String op, IReal result, int precision) throws WrongOperationException;

    /**
     * Prints error message
     * @param s error code
     */
    void errorMessage(String s);
}
