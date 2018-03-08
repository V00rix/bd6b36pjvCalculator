package com.cvut.bd6b36pjv.calculator.domain;

import com.cvut.bd6b36pjv.calculator.api.IReal;
import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Real implements IReal {
    /**
     * Number of digits after the floating point
     */
    private int _precision = 0;

    private double value;

    public Real(double value) {
        this.value = value;
    }

    public Real(int value) {
        this.value = value;
    }

    public Real(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public IReal add(IReal b) {
        return new Real(this.value + b.doubleValue());
    }

    @Override
    public IReal subtract(IReal b) {
        return new Real(this.value - b.doubleValue());
    }

    @Override
    public IReal multiplyBy(IReal b) {
        return new Real(this.value * b.doubleValue());
    }

    @Override
    public IReal divideBy(IReal b) throws DivisionByZeroException {
        if (Double.compare(b.doubleValue(), 0.0) == 0)
            throw new DivisionByZeroException();
        return new Real(this.value / b.doubleValue());
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    @Override
    public int intValue() {
        return (int) this.value;
    }

    @Override
    public int precision() {
        return this._precision;
    }

    @Override
    public String stringValue() {
        return String.valueOf(this.value);
    }

    @Override
    public String stringValue(int precision) {
        if (this.value < Math.pow(10, 16)) {
            StringBuilder formatString = new StringBuilder("#");

            if (precision > 0) {
                formatString.append(".");
                for (int i = 0; i < precision; i++) {
                    formatString.append("#");
                }
            }

            DecimalFormat df = new DecimalFormat(formatString.toString());
            df.setRoundingMode(RoundingMode.HALF_UP);
            return df.format(this.doubleValue());
        }
        return this.stringValue();
    }

    @Override
    public void setValue(IReal value) {
        this.value = value.doubleValue();
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void setValue(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public void appendDigit(int digit) {
        this.value = this.value * 10 + digit;
        if (this._precision > 0) {
            this.value /= 10;
        }
    }

    @Override
    public void removeDigit() {
        if (this._precision < 1) {
            this.value -= (this.value % 10);
            this.value /= 10;
        } else {
            this.value = Double.parseDouble(this.stringValue(--this._precision));
        }
    }
}
