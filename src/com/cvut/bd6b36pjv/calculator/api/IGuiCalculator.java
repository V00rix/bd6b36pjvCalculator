package com.cvut.bd6b36pjv.calculator.api;

import com.cvut.bd6b36pjv.calculator.domain.enumeration.CalculatorOperation;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

import java.math.BigDecimal;

/**
 * Gui calculator API
 */
public interface IGuiCalculator extends ICalculator {
    /**
     * Operation setter handler
     * @param op Operation
     * @see CalculatorOperation
     * @return Gui display result
     * @throws WrongOperationException WrongOperationException
     */
    BigDecimal setOperation(String op) throws WrongOperationException;

    /**
     * Digit input handler
     * @param number Digit
     * @return Gui display result
     */
    BigDecimal input(char number);

    /**
     * Digit input handler
     * @param number Digit
     * @return Gui display result
     */
    BigDecimal input(int number);

    int getPrecision();
}
