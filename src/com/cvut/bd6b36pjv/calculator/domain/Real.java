package com.cvut.bd6b36pjv.calculator.domain;

import com.cvut.bd6b36pjv.calculator.api.IReal;
import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Basic real numbers implementation
 */
public class Real implements IReal {
    /**
     * Number of digits after the floating point
     */
    private int precision;

    /**
     * Flag-workaround for appending/removing digits for floating point numbers
     */
    private boolean appending;

    /**
     * Value container field
     */
    private double value;


    // Constructors

    /**
     * Empty (default) constructor
     */
    public Real() {
        this.setDefault();
    }

    /**
     * Create real from double
     *
     * @param value Double value
     */
    public Real(double value) {
        this.value = value;
    }

    /**
     * Create real from double
     *
     * @param value Double value
     */
    public Real(double value, int precision) {
        this.setDefault();
        this.value = value;
        this.precision = precision;
    }

    /**
     * Create real from int
     *
     * @param value Int value
     */
    public Real(int value) {
        this.value = value;
    }

    /**
     * Create real from string
     *
     * @param value String to be parsed
     */
    public Real(String value) {
        this.value = Double.parseDouble(value);
    }

    /**
     * Create real from another real (copy constructor)
     *
     * @param real Real number
     */
    public Real(Real real) {
        this.value = real.value;
        this.precision = real.precision;
        this.appending = real.appending;
    }

    @Override
    public IReal add(IReal b) {
        return new Real(this.value + b.doubleValue(), this.precision > b.getPrecision() ? this.precision : b.getPrecision());
    }

    @Override
    public IReal subtract(IReal b) {
        return new Real(this.value - b.doubleValue(), this.precision > b.getPrecision() ? this.precision : b.getPrecision());
    }

    @Override
    public IReal multiplyBy(IReal b) {
        return new Real(this.value * b.doubleValue(), this.precision > b.getPrecision() ? this.precision : b.getPrecision());
    }

    @Override
    public IReal divideBy(IReal b) throws DivisionByZeroException {
        if (Double.compare(b.doubleValue(), 0.0) == 0)
            throw new DivisionByZeroException();
        return new Real(this.value / b.doubleValue(), this.precision > b.getPrecision() ? this.precision : b.getPrecision());
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
    public IReal setDefault() {
        this.precision = 0;
        this.value = 0;
        this.appending = true;
        return this;
    }

    @Override
    public int getPrecision() {
        return this.precision;
    }

    @Override
    public void setFloatingPoint() {
        if (this.precision < 1) {
            this.precision = 1;
            this.appending = true;
        }
    }

    @Override
    public String stringValue() {
        return this.stringValue(this.precision);
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
            String formatted = df.format(this.doubleValue());
            return formatted + (precision == 1 && !formatted.contains(".") ? "." : "");
        }
        return this.stringValue();
    }

    @Override
    public IReal setValue(IReal value) {
        this.value = value.doubleValue();
        this.precision = value.getPrecision();
        return this;
    }

    @Override
    public IReal setValue(double value) {
        this.value = value;
        return new Real(this);
    }

    @Override
    public IReal setValue(double value, int precision) {
        this.value = value;
        this.precision = precision;
        return new Real(this);
    }

    @Override
    public IReal setValue(int value) {
        this.value = value;
        return new Real(this);
    }

    @Override
    public IReal setValue(String value) {
        this.value = Double.parseDouble(value);
        return new Real(this);
    }

    @Override
    public IReal appendDigit(int digit) {
        if (this.precision > 0) {
            if (!this.appending) {
                this.appending = true;
                this.precision++;
            }
            this.value = this.value + (this.value < 0 ? -1.0 : 1.0) * digit / Math.pow(10, precision++);
            System.out.println(this.precision);
        } else {
            this.value =  this.value * 10 + (this.value < 0 ? -1.0 : 1.0) * digit;
        }
        System.out.println(this.value);
        return this;
    }

    @Override
    public IReal removeDigit() {
        if (this.precision < 1) {
            this.value -= (this.value % 10);
            this.value /= 10;
        } else {
            if (this.appending) {
                this.appending = false;
                this.precision--;
            }
            this.value *= Math.pow(10, precision);
            this.value -= (this.value < 0 ? -1.0 : 1.0)*(this.value % 10);
            this.value /= Math.pow(10, precision--);
            System.out.println(this.precision);
        }
        System.out.println(this.value);
        return this;
    }
}
