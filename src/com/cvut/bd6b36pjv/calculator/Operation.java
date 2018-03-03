package com.cvut.bd6b36pjv.calculator;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

public enum Operation {
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION,
    QUIT;

    public char getSign() throws WrongOperationException {
        switch (this) {
            case ADDITION:
                return '+';
            case SUBTRACTION:
                return '-';
            case MULTIPLICATION:
                return '*';
            case DIVISION:
                return '/';
            default:
                throw new WrongOperationException();
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ADDITION:
                return "+";
            case SUBTRACTION:
                return "-";
            case MULTIPLICATION:
                return "*";
            case DIVISION:
                return "/";
            case QUIT:
                return "quit";
            default:
                return "NotAnOperation";
        }
    }
}
