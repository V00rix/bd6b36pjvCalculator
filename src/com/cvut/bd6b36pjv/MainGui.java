package com.cvut.bd6b36pjv;

import com.cvut.bd6b36pjv.calculator.Calculator;
import com.cvut.bd6b36pjv.calculator.DefaultCalculator;
import com.cvut.bd6b36pjv.gui.Gui;
import javafx.application.Application;

import java.util.concurrent.CountDownLatch;

/**
 * Main application
 */
public class MainGui {
    /**
     * Application entry point
     */
    public static void main(String[] args) throws InterruptedException {
        Calculator calc = new DefaultCalculator();

        new Thread() {
            @Override
            public void run() {
                Application.launch(Gui.class);
            }
        }.start();

        Gui.latch.await();
        Gui.calculator = calc;

        System.out.println("calc");
    }
}
