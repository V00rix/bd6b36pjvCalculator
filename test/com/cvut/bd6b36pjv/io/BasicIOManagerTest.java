package com.cvut.bd6b36pjv.io;

import com.cvut.bd6b36pjv.calculator.Operation;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class BasicIOManagerTest {

    IOManager io = new BasicIOManager();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }


    @Test
    public void readOperation() throws WrongOperationException {
        ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
        System.setIn(in);
        Scanner keyboard = new Scanner(in);
        // do your thing



        int input = keyboard.nextInt();
        Operation op = io.readOperation();

//        System.out.print("hello");
        assertEquals("Vyber operaci (1-soucet, 2-rozdil, 3-soucin, 4-podil):",
                outContent.toString());



        // optionally, reset System.in to its original
        System.setIn(System.in);
    }

    @Test
    public void readOperands() {
    }

    @Test
    public void readOperands1() {
    }

    @Test
    public void readPrecision() {
    }

    @Test
    public void printResult() throws WrongOperationException {
    }

    @Test
    public void errorMessage() {

    }
}