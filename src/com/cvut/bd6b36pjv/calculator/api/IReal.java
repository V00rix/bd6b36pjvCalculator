package com.cvut.bd6b36pjv.calculator.api;

import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;

public interface IReal {
    IReal add(IReal b);
    IReal subtract(IReal b);
    IReal multiplyBy(IReal b);
    IReal divideBy(IReal b) throws DivisionByZeroException;

    double doubleValue();
    int intValue();
    int precision();
    String stringValue();
    String stringValue(int precision);

    void setValue(IReal value);
    void setValue(double value);
    void setValue(int value);
    void setValue(String value);

    void appendDigit(int digit);
    void removeDigit();
}
