package com.cvut.bd6b36pjv;

import com.cvut.bd6b36pjv.calculator.Calculator;
import com.cvut.bd6b36pjv.calculator.DefaultCalculator;
import com.cvut.bd6b36pjv.calculator.Operation;
import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;
import com.cvut.bd6b36pjv.exceptions.WrongInputException;
import com.cvut.bd6b36pjv.io.BasicIOManager;
import com.cvut.bd6b36pjv.io.IOManager;

import static com.cvut.bd6b36pjv.calculator.Operation.QUIT;

/**
 * Main application
 */
public class Main {
    /**
     * Application entry point
     */
    public static void main(String[] args) {
        IOManager io = new BasicIOManager();
        Calculator calc = new DefaultCalculator();

        Operation op;

        while (true) {
            try {
                op = io.readOperation();
                if (op == QUIT)
                    break;

                double[] operands = io.readOperands(op);
                int precision = io.readPrecision();

                io.printResult(operands, op, calc.compute(op, operands), precision);
            }
            catch (WrongInputException e) {
                op = null;
            } catch (UnsupportedOperationException e) {
                System.out.println("Operation isn't yet implemented");
                op = null;
            }
        }
    }
}
