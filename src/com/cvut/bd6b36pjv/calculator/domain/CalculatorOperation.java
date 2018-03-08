package com.cvut.bd6b36pjv.calculator.domain;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

public abstract class CalculatorOperation extends BasicCalculatorOperation {
    public static final String SQUARE_ROOT = "√";
    public static final String NOTHING = "null";
    public static final String SQUARE = "x^2";
    public static final String OND_DIVIDED = "1/x";

    public static String Code(String v) throws WrongOperationException {
        switch (v){
            case CalculatorOperation.ADDITION:
                return CalculatorOperation.ADDITION;
            case CalculatorOperation.SUBTRACTION:
                return CalculatorOperation.SUBTRACTION;
            case CalculatorOperation.MULTIPLICATION:
                return CalculatorOperation.MULTIPLICATION;
            case CalculatorOperation.DIVISION:
                return CalculatorOperation.DIVISION;
            case CalculatorOperation.SQUARE_ROOT:
                return CalculatorOperation.SQUARE_ROOT;
            case CalculatorOperation.SQUARE:
                return CalculatorOperation.SQUARE;
            case CalculatorOperation.OND_DIVIDED:
                return CalculatorOperation.OND_DIVIDED;
            case CalculatorOperation.NOTHING:
                return CalculatorOperation.NOTHING;
            default:
                throw new WrongOperationException();
        }
    }
}
