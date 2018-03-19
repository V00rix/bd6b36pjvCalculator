package com.cvut.bd6b36pjv.calculator.implementation;

import com.cvut.bd6b36pjv.calculator.api.IGuiCalculator;
import com.cvut.bd6b36pjv.calculator.domain.enumeration.CalculatorOperation;
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
    public boolean floatInput;
    private int zeroesOnInput;

    /**
     * New instance of GuiCalculator
     */
    public GuiCalculator() {
        setDefaults();
    }


    /**
     * Default settings
     */
    private void setDefaults() {
        result = new BigDecimal("0");
        operands = new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0")
        };
        resultViewed = true;
        unaryOccurred = false;
        floatInput = false;
        precision = 10;
        int zeroesOnInput = 0;
        operation = CalculatorOperation.ADDITION;
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
                return operands[0].divide(operands[1], precision, RoundingMode.HALF_UP);
            default:
                throw new WrongOperationException();
        }
    }

    @Override
    public String setOperation(String op) throws WrongOperationException {
        System.out.println("operation input: " + op);

        floatInput = false;
        /* Special operations */
        switch (op) {
            case CalculatorOperation.RESULT:
                return resultOperation().stripTrailingZeros().toPlainString();
            case CalculatorOperation.FLUSH:
                setDefaults();
            case CalculatorOperation.ERASE:
                operands[1] = new BigDecimal("0");
                return operands[1].toPlainString();
            /* Unary operators */
            case CalculatorOperation.ONE_DIVIDED:
                if (resultViewed) {
                    result = operands[0] = new BigDecimal("1.0").divide(result, precision, RoundingMode.HALF_UP);
                    return result.stripTrailingZeros().toPlainString();
                } else {
                    operands[1] = new BigDecimal("1.0").divide(operands[1], precision, RoundingMode.HALF_UP);
                    return operands[1].stripTrailingZeros().toPlainString();
                }
            case CalculatorOperation.SQUARE:
                if (resultViewed) {
                    result = operands[0] = result.multiply(result);
                    return result.stripTrailingZeros().toPlainString();
                } else {
                    operands[1] = operands[1].multiply(operands[1]);
                    return operands[1].stripTrailingZeros().toPlainString();
                }
            case CalculatorOperation.SQUARE_ROOT:
                if (resultViewed) {
                    result = operands[0] = new BigDecimal(Math.sqrt(result.doubleValue()));
                    result = operands[0].setScale(precision, RoundingMode.HALF_UP);
                    return result.stripTrailingZeros().toPlainString();
                } else {
                    operands[1] = new BigDecimal(Math.sqrt(operands[1].doubleValue()));
                    operands[1].setScale(precision, RoundingMode.HALF_UP);
                    return operands[1].stripTrailingZeros().toPlainString();
                }
            case CalculatorOperation.PERCENTAGE:
                if (resultViewed) {
                    result = operands[0] = result.divide(new BigDecimal("100"), precision, RoundingMode.HALF_UP).multiply(result);
                    return result.stripTrailingZeros().toPlainString();
                } else {
                    operands[1] = operands[0].divide(new BigDecimal("100"), precision, RoundingMode.HALF_UP).multiply(operands[1]);
                    return operands[1].stripTrailingZeros().toPlainString();
                }
            case CalculatorOperation.SIGN_SWITCH:
                if (resultViewed) {
                    result = operands[0] = result.multiply(new BigDecimal("-1.0"));
                    return result.stripTrailingZeros().toPlainString();
                } else {
                    operands[1] = operands[1].multiply(new BigDecimal("-1.0"));
                    return operands[1].stripTrailingZeros().toPlainString();
                }
            case CalculatorOperation.REMOVE_LAST:
                return removeLast().toPlainString();
            case CalculatorOperation.FLOAT:
                return setFloatingPoint().toPlainString();
            default:
                break;
        }

        if (resultViewed) {
            operands[1] = new BigDecimal("0");
            operation = op;
            resultViewed = false;

//            this.precision = this.result.getPrecision();
            return result.stripTrailingZeros().toPlainString();
        }

        /* Simple binary operations */
        unaryOccurred = false;
        BigDecimal result = compute(operation, new BigDecimal[]{operands[0], operands[1]});

        System.out.println(result.toPlainString());
//        System.out.println(result.getPrecision());

        operands[0] = result;
        this.result = result;

        operands[1] = new BigDecimal("0");
        operation = op;

        resultViewed = false;

//        this.precision = result.getPrecision();
        return result.stripTrailingZeros().toPlainString();
    }

    /**
     * .RESULT ('=') operation handler
     *
     * @return Operation result
     * @throws WrongOperationException WrongOperationException
     */
    private BigDecimal resultOperation() throws WrongOperationException {
        unaryOccurred = false;

        BigDecimal result = compute(operation, new BigDecimal[]{this.result, operands[1]});

        System.out.println(result.toPlainString());
        System.out.println("Precision: " + result.precision());
        System.out.println("Scale: " + result.scale());

        operands[0] = this.result = result;

        operands[1] = new BigDecimal("0");

        resultViewed = true;

//        this.precision = this.result.getPrecision();
        return this.result.stripTrailingZeros();
    }

    @Override
    public String input(char number) {
        System.out.println("Digit input: " + number);
        System.out.println("Result viewed: " + resultViewed);

        if (resultViewed) {
            setDefaults();
            resultViewed = false;
        }

        if (unaryOccurred) {
            unaryOccurred = false;
            operands[1] = new BigDecimal("0");
        }

        if (floatInput) {
            String strVal = operands[1].toPlainString();
            int scale = operands[1].scale() + 1;
            operands[1] = new BigDecimal(strVal + (strVal.contains(".") ? "" : ".") + number);
            operands[1].setScale(scale, RoundingMode.HALF_UP);
            return operands[1].toPlainString();
        }

        operands[1] = new BigDecimal(operands[1].toPlainString() + "" + number);
        return operands[1].toPlainString();

//        return this.input(Character.getNumericValue(number));
    }

    @Override
    public String input(int number) {
        return input(Character.forDigit(number, 10));
    }

    @Override
    public int getPrecision() {
        return precision;
    }

    /**
     * Last digit removal operation handler
     *
     * @return Operation result
     */
    private BigDecimal removeLast() {
        String strVal = operands[1].toPlainString();
        if (!(resultViewed || unaryOccurred)) {
            if (strVal.length() == 1)
                operands[1] = new BigDecimal(0);
            if (strVal.length() > ((strVal.charAt(0) == '-' ? 2 : 1)))
                operands[1] = new BigDecimal(strVal.substring(0, strVal.length() - 1));
        }
        if (!operands[1].toPlainString().contains("."))
            floatInput = false;
//        this.precision = this.operands[1].getPrecision();
        return operands[1];
    }

    /**
     * @return
     */
    private BigDecimal setFloatingPoint() {
//        System.out.println(new BigDecimal("0.0").stripTrailingZeros().toPlainString());
//        int scale = operands[1].scale();
//        if (scale < 1)
//            this.operands[1].setScale(1, RoundingMode.HALF_UP);
        floatInput = true;
        return operands[1];
    }
}
