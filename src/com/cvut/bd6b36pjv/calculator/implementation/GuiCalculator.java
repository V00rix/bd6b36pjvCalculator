package com.cvut.bd6b36pjv.calculator.implementation;

import com.cvut.bd6b36pjv.calculator.api.IGuiCalculator;
import com.cvut.bd6b36pjv.calculator.domain.enumeration.CalculatorOperation;
import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GuiCalculator implements IGuiCalculator {
    private BigDecimal[] operands;

    private BigDecimal result;
    private int precision;

    private String operation;

    private boolean resultViewed;
    private boolean unaryOccurred;

    /**
     * New instance of GuiCalculator
     */
    public GuiCalculator() {
        this.setDefaults();
    }


    /**
     * Default settings
     */
    private void setDefaults() {
        this.result = new BigDecimal("0");
        this.operands = new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0")
        };
        this.resultViewed = true;
        this.unaryOccurred = false;
        this.precision = 10;
        this.operation = CalculatorOperation.ADDITION;
    }

    @Override
    public BigDecimal compute(String op, BigDecimal[] operands) throws WrongOperationException {
        System.out.println(operands[1].toPlainString());
        System.out.println(operands[0].toPlainString());
        System.out.println(op);
        switch (op) {
            case CalculatorOperation.ADDITION:
                return operands[0].add(operands[1]);
            case CalculatorOperation.SUBTRACTION:
                return operands[0].subtract(operands[1]);
            case CalculatorOperation.MULTIPLICATION:
                return operands[0].multiply(operands[1]);
            case CalculatorOperation.DIVISION:
                return operands[0].divide(operands[1], this.precision, RoundingMode.HALF_UP);
            default:
                throw new WrongOperationException();
        }
    }

    @Override
    public BigDecimal setOperation(String op) throws WrongOperationException {
        System.out.println("operation input: " + op);

        /* Special operations */
        switch (op) {
            case CalculatorOperation.RESULT:
                return this.resultOperation();
            case CalculatorOperation.FLUSH:
                this.setDefaults();
            case CalculatorOperation.ERASE:
                return this.operands[1] = new BigDecimal("0");
            /* Unary operators */
            case CalculatorOperation.ONE_DIVIDED:
            case CalculatorOperation.SQUARE:
            case CalculatorOperation.SQUARE_ROOT:
            case CalculatorOperation.PERCENTAGE:
                return this.unaryOperation(op);
            case CalculatorOperation.SIGN_SWITCH:
                if (this.resultViewed) {
                    return this.result = this.operands[0] = this.result.multiply(new BigDecimal("-1.0"));
                } else {
                    return this.operands[1] = this.operands[1].multiply(new BigDecimal("-1.0"));
                }
            case CalculatorOperation.REMOVE_LAST:
                return this.removeLast();
            case CalculatorOperation.FLOAT:
                return this.setFloatingPoint();
            default:
                break;
        }

        if (this.resultViewed) {
            this.operands[1] = new BigDecimal("0");
            this.operation = op;
            this.resultViewed = false;

//            this.precision = this.result.getPrecision();
            return this.result;
        }

        /* Simple binary operations */
        this.unaryOccurred = false;
        BigDecimal result = compute(this.operation, new BigDecimal[]{this.operands[0], this.operands[1]});

        System.out.println(result.toPlainString());
//        System.out.println(result.getPrecision());

        this.operands[0] = result;
        this.result = result;

        this.operands[1] = new BigDecimal("0");
        this.operation = op;

        this.resultViewed = false;

//        this.precision = result.getPrecision();
        return result;
    }

    /**
     * Unary operations handler (applies only to current operand)
     *
     * @param op Operation
     * @return Operation result
     * @throws WrongOperationException WrongOperationException
     */
    private BigDecimal unaryOperation(String op) throws WrongOperationException {
        this.unaryOccurred = true;
        switch (op) {
            case CalculatorOperation.ONE_DIVIDED:
                return this.operands[1] = new BigDecimal("1.0").divide(this.operands[1], this.precision, RoundingMode.HALF_UP);
            case CalculatorOperation.SQUARE:
                return this.operands[1] = this.operands[1].multiply(this.operands[1]);
            case CalculatorOperation.SQUARE_ROOT:
                this.operands[1] = new BigDecimal(Math.sqrt(this.operands[1].doubleValue()));
                return this.operands[1].setScale(this.precision, RoundingMode.HALF_UP);
            case CalculatorOperation.PERCENTAGE:
                return this.operands[1] = this.operands[0].divide(new BigDecimal("100"), this.precision, RoundingMode.HALF_UP).multiply(this.operands[1]);
            default:
                throw new WrongOperationException();
        }
    }

    /**
     * .RESULT ('=') operation handler
     *
     * @return Operation result
     * @throws WrongOperationException WrongOperationException
     */
    private BigDecimal resultOperation() throws WrongOperationException {
        this.unaryOccurred = false;

        BigDecimal result = compute(this.operation, new BigDecimal[]{this.result, this.operands[1]});

        System.out.println(result.toPlainString());
        System.out.println("Precision: " + result.precision());
        System.out.println("Scale: " + result.scale());

        this.operands[0] = this.result = result;

        this.operands[1] = new BigDecimal("0");

        this.resultViewed = true;

//        this.precision = this.result.getPrecision();
        return this.result;
    }

    @Override
    public BigDecimal input(char number) {
        System.out.println("Digit input: " + number);
        System.out.println("Result viewed: " + this.resultViewed);

        if (this.resultViewed) {
            this.setDefaults();
            this.resultViewed = false;
        }

        if (this.unaryOccurred) {
            this.unaryOccurred = false;
            this.operands[1] = new BigDecimal("0");
        }

        return this.operands[1] = new BigDecimal(this.operands[1] + "" + number);
//        return this.input(Character.getNumericValue(number));
    }

    @Override
    public BigDecimal input(int number) {
        return this.input(Character.forDigit(number, 10));
    }

    @Override
    public int getPrecision() {
        return this.precision;
    }

    /**
     * Last digit removal operation handler
     *
     * @return Operation result
     */
    private BigDecimal removeLast() {
        String strVal = this.operands[1].toPlainString();
        if (!(this.resultViewed || this.unaryOccurred)) {
            if (strVal.length() > ((strVal.charAt(0) == '-' ? 2 : 1)))
            return this.operands[1] = new BigDecimal(strVal.substring(0, strVal.length() - 1));
        }
//        this.precision = this.operands[1].getPrecision();
        return this.operands[1];
    }

    /**
     * @return
     */
    private BigDecimal setFloatingPoint() {
        int scale = operands[1].scale();
        if (scale < 1)
            this.operands[1].setScale(1, RoundingMode.HALF_UP);
        return this.operands[1];
    }
}
