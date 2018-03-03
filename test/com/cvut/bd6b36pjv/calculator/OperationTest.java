package com.cvut.bd6b36pjv.calculator;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.cvut.bd6b36pjv.calculator.Operation.*;
import static org.junit.Assert.*;

public class OperationTest {
    @Test
    public void getSign() throws WrongOperationException {
        assertEquals('+', ADDITION.getSign());
        assertEquals('-', SUBTRACTION.getSign());
        assertEquals('*', MULTIPLICATION.getSign());
        assertEquals('/', DIVISION.getSign());
    }


    @Test(expected = WrongOperationException.class)
    public void getFail() throws WrongOperationException {
        assertEquals('3', QUIT.getSign());
    }

    @Test
    public void testToString() {
        assertEquals("+", ADDITION.toString());
        assertEquals("-", SUBTRACTION.toString());
        assertEquals("*", MULTIPLICATION.toString());
        assertEquals("/", DIVISION.toString());
        assertEquals("quit", QUIT.toString());
    }

}