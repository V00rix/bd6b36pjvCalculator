package com.cvut.bd6b36pjv.calculator.domain.enumeration;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

/**
 * Basic calculator operations
 */
public abstract class BasicCalculatorOperation extends Operation {
    public static final String ADDITION = "+";
    public static final String SUBTRACTION = "-";
    public static final String MULTIPLICATION = "*";
    public static final String DIVISION = "/";
}