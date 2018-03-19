package com.cvut.bd6b36pjv.calculator.api;

import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;

/**
 * Real numbers API
 */
public interface IReal {
    // Mathematical operations

    /**
     * Add two real numbers
     *
     * @param b Second operand
     * @return Result of operation
     */
    IReal add(IReal b);

    /**
     * Subtract second real from first
     *
     * @param b Second operand
     * @return Result of operation
     */
    IReal subtract(IReal b);

    /**
     * Multiply two real numbers
     *
     * @param b Second operand
     * @return Result of operation
     */
    IReal multiplyBy(IReal b);

    /**
     * Divide first real by second
     *
     * @param b Second operand
     * @return Result of operation
     * @throws DivisionByZeroException
     */
    IReal divideBy(IReal b) throws DivisionByZeroException;

    // Getters, Conversions

    /**
     * Double value of real
     *
     * @return Double
     */
    double doubleValue();

    /**
     * Int value of real
     *
     * @return Int
     */
    int intValue();

    /**
     * Display precision
     *
     * @return Precision
     */
    void setFloatingPoint();

    /**
     * Convert to string
     *
     * @return Real as string
     */
    String stringValue();

    /**
     * Convert to string and specify precision
     *
     * @param precision Number of digits after floating point
     * @return Real as string with precision
     */
    String stringValue(int precision);

    // Setters, Assigners

    /**
     * Defaults
     */
    IReal setDefault();

    int getPrecision();

    /**
     * Assign value to real number
     *
     * @param value Real number
     * @return Result of operation
     */
    IReal setValue(IReal value);

    /**
     * Assign value to real number
     * @param value double number
     * @param precision precision
     * @return Result of operation
     */
    IReal setValue(double value, int precision);

    /**
     * Assign value to real number
     *
     * @param value Double number
     * @return Result of operation
     */
    IReal setValue(double value);

    /**
     * Assign value to real number
     *
     * @param value Integer number
     * @return Result of operation
     */
    IReal setValue(int value);

    /**
     * Assign value to real number
     *
     * @param value String to be parsed
     * @return Result of operation
     */
    IReal setValue(String value);

    // String-like manipulations

    /**
     * Append digit to the end of a number
     *
     * @param digit Digit to be apended
     * @return Result of operation
     */
    IReal appendDigit(int digit);

    /**
     * Remove last digit
     *
     * @return Result of operation
     */
    IReal removeDigit();
}
