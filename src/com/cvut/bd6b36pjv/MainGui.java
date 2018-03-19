package com.cvut.bd6b36pjv;

import com.cvut.bd6b36pjv.calculator.api.IGuiCalculator;
import com.cvut.bd6b36pjv.calculator.domain.enumeration.CalculatorOperation;
import com.cvut.bd6b36pjv.calculator.implementation.GuiCalculator;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;
import com.cvut.bd6b36pjv.gui.Gui;
import javafx.application.Application;

/**
 * Main application
 */
public class MainGui {
    /**
     * Application entry point
     */
    public static void main(String[] args) throws InterruptedException, IllegalAccessException, WrongOperationException {
        System.out.println(CalculatorOperation.Code(CalculatorOperation.class, "+"));

        IGuiCalculator calc = new GuiCalculator();

        new Thread() {
            @Override
            public void run() {
                Application.launch(Gui.class);
            }
        }.start();

        Gui.latch.await();
        Gui.calculator = calc;
    }
}
