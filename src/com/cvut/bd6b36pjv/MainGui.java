package com.cvut.bd6b36pjv;

import com.cvut.bd6b36pjv.calculator.api.ICalculator;
import com.cvut.bd6b36pjv.calculator.api.IGuiCalculator;
import com.cvut.bd6b36pjv.calculator.implementation.DefaultCalculator;
import com.cvut.bd6b36pjv.calculator.implementation.GuiCalculator;
import com.cvut.bd6b36pjv.gui.Gui;
import javafx.application.Application;

/**
 * Main application
 */
public class MainGui {
    /**
     * Application entry point
     */
    public static void main(String[] args) throws InterruptedException {
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
