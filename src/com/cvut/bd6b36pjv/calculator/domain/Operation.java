package com.cvut.bd6b36pjv.calculator.domain;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

public abstract class Operation {
    public static final String QUIT = "quit";

    public static String Code(String v) throws WrongOperationException {
        switch (v) {
            case BasicCalculatorOperation.QUIT:
            case "q":
                return BasicCalculatorOperation.QUIT;
            default:
                throw new WrongOperationException();
        }
    }
}
