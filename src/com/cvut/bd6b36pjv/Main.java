package com.cvut.bd6b36pjv;

import com.cvut.bd6b36pjv.calculator.api.ICalculator;
import com.cvut.bd6b36pjv.calculator.api.IReal;
import com.cvut.bd6b36pjv.calculator.domain.BasicCalculatorOperation;
import com.cvut.bd6b36pjv.calculator.implementation.DefaultCalculator;
import com.cvut.bd6b36pjv.exceptions.WrongInputException;
import com.cvut.bd6b36pjv.io.implementation.DefaultIOManager;
import com.cvut.bd6b36pjv.io.api.IOManager;

import static com.cvut.bd6b36pjv.calculator.domain.BasicCalculatorOperation.QUIT;

/**
 * Main application
 */
public class Main {
    /**
     * Application entry point
     */
    public static void main(String[] args) {
        IOManager io = new DefaultIOManager();
        ICalculator calc = new DefaultCalculator();

        String op;

        while (true) {
            try {
                op = io.readOperation();
                if (op.equals(BasicCalculatorOperation.QUIT))
                    break;

                IReal[] operands = io.readOperands(op);
                int precision = io.readPrecision();

                io.printResult(operands, op, calc.compute(op, operands), precision);
            }
            catch (WrongInputException e) {
                op = null;
            } catch (UnsupportedOperationException e) {
                System.out.println("BasicCalculatorOperation isn't yet implemented");
                op = null;
            }
        }
    }
}
