package com.cvut.bd6b36pjv.io;

import com.cvut.bd6b36pjv.calculator.Operation;
import com.cvut.bd6b36pjv.exceptions.WrongOperandException;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

/**
 * API for console interactions
 */
public interface IOManager {
    /**
     * Prints message and parses input into operation
     * @return Operation to be computed
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    Operation readOperation() throws WrongOperationException;

    /**
     * Reads two real number operands
     * @param op Operation to specify names for operands
     * @return Two real numbers
     * @throws WrongOperandException See {@see WrongOperandException}
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    double[] readOperands(Operation op) throws WrongOperandException, WrongOperationException;

    /**
     * Reads two real number operands
     * @return Two real numbers
     * @throws WrongOperandException See {@see WrongOperandException}
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    double[] readOperands() throws WrongOperandException, WrongOperationException;

    /**
     * Reads calculation precision
     * @return Precision
     * @throws WrongOperandException See {@see WrongOperationException}
     */
    int readPrecision() throws WrongOperandException;

    /**
     * Prints calculation string
     * @param operands Operands
     * @param op Operation
     * @param result Result of calculation
     * @param precision Precision of display (not calculation)
     * @throws WrongOperationException See {@see WrongOperationException}
     */
    void printResult(double[] operands, Operation op, double result, int precision) throws WrongOperationException;

    /**
     * Prints error message
     * @param s error code
     */
    void errorMessage(String s);
}
