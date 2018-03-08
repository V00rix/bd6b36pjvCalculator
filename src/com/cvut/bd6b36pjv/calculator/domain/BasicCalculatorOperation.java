package com.cvut.bd6b36pjv.calculator.domain;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

public abstract class BasicCalculatorOperation extends Operation {
    public static final String ADDITION = "+";
    public static final String SUBTRACTION = "-";
    public static final String MULTIPLICATION = "*";
    public static final String DIVISION = "/";

    public static String Code(String v) throws WrongOperationException {
        switch (v){
            case BasicCalculatorOperation.ADDITION:
            case "1":
                return BasicCalculatorOperation.ADDITION;
            case BasicCalculatorOperation.SUBTRACTION:
            case "2":
                return BasicCalculatorOperation.SUBTRACTION;
            case BasicCalculatorOperation.MULTIPLICATION:
            case "3":
                return BasicCalculatorOperation.MULTIPLICATION;
            case BasicCalculatorOperation.DIVISION:
            case "4":
                return BasicCalculatorOperation.DIVISION;
            case BasicCalculatorOperation.QUIT:
            case "q":
                return BasicCalculatorOperation.QUIT;
                default:
                    throw new WrongOperationException();
        }
    }
}