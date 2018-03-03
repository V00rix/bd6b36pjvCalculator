package com.cvut.bd6b36pjv.calculator;

import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class DefaultCalculatorTest {
    private static double delta = 0.0000000000001;

    @Parameter(value = 0)
    public double numberA;

    @Parameter(value = 1)
    public Operation operation;


    @Parameter(value = 2)
    public double numberB;

    @Parameter(value = 3)
    public double expected;

    @Parameterized.Parameters(name = "{index}: compute({0} {1} {2}) = {3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, Operation.ADDITION, 1, 2},
                {1.9123123, Operation.ADDITION, 32.321312, 34.2336243},
                {8, Operation.SUBTRACTION, 2, 6},
                {0, Operation.SUBTRACTION, 2, -2},
                {-0, Operation.SUBTRACTION, 0, 0},
                {-0, Operation.SUBTRACTION, -0, 0},
                {0, Operation.SUBTRACTION, -0, 0},
                {-10, Operation.SUBTRACTION, -20, 10},
                {4, Operation.MULTIPLICATION, 5, 20},
                {4, Operation.MULTIPLICATION, 0.5, 2},
                {4, Operation.MULTIPLICATION, -10, -40},
                {-0, Operation.MULTIPLICATION, -10, 0},
                {-10, Operation.MULTIPLICATION, -10, 100},
                {5, Operation.DIVISION, 5, 1},
                {5, Operation.DIVISION, 2, 2.5}
        });
    }

    private Calculator calc = new DefaultCalculator();

    @Test
    public void checkZero() {
        assertEquals(0, calc.getLastResult(), delta);
    }

    @Test
    public void compute() throws WrongOperationException {
        assertEquals(expected, calc.compute(operation, new double[] {numberA, numberB}), delta);
        assertEquals(expected, calc.getLastResult(), delta);
    }

    @Test(expected = DivisionByZeroException.class)
    public void computeZero() throws WrongOperationException {
        assertEquals(1, calc.compute(Operation.DIVISION, new double[] {100, 0}), 0.0000000000001);
    }
    @Test(expected = WrongOperationException.class)
    public void computeOperation() throws WrongOperationException {
        assertEquals(1, calc.compute(Operation.QUIT, new double[] {100, 0}), 0.0000000000001);
    }



}