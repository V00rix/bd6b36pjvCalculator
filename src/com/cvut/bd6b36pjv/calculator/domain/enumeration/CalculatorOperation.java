package com.cvut.bd6b36pjv.calculator.domain.enumeration;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

/**
 * Complex gui operations
 */
public abstract class CalculatorOperation extends BasicCalculatorOperation {
    public static final String SQUARE_ROOT = "√";
    public static final String NOTHING = "null";
    public static final String RESULT = "=";
    public static final String SQUARE = "x^2";
    public static final String ONE_DIVIDED = "1/x";
    public static final String SIGN_SWITCH = "±";
    public static final String FLOAT = ".";
    public static final String REMOVE_LAST = "<";
    public static final String ERASE = "CE";
    public static final String FLUSH = "C";
    public static final String PERCENTAGE = "%";
}
