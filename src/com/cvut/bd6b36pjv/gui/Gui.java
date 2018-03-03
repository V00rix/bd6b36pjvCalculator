package com.cvut.bd6b36pjv.gui;

import com.cvut.bd6b36pjv.calculator.Calculator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

public class Gui extends Application {
    public static final CountDownLatch latch = new CountDownLatch(1);

    public static Calculator calculator;
//
//    public Gui(Calculator calc) {
//        this.calculator = calc;
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Calculator");


        GridPane root = new GridPane();

        for (int i = 0; i < 4; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(25);
            root.getColumnConstraints().add(col);
        }

        Button[] buttons = createButtons();

        System.out.println(buttons.length);
        for (int i = 0; i < buttons.length; i++) {
            root.add(buttons[i], i % 4, i / 4);
        }
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        scene.getStylesheets().add("com/cvut/bd6b36pjv/gui/styles/styles.css");

        primaryStage.show();
        latch.countDown();
    }

    private static Button[] createButtons() {


        Label l1 = new Label("Some text");


        Button[] buttons = new Button[24];


        buttons[0] = new Button("%");
        buttons[1] = new Button("√");
        buttons[2] = new Button("x^2");
        buttons[3] = new Button("1/x");
        buttons[4] = new Button("CE");
        buttons[5] = new Button("C");
        buttons[6] = new Button("<");
        buttons[7] = new Button("÷");
        buttons[8] = new Button("7");
        buttons[9] = new Button("8");
        buttons[10] = new Button("9");
        buttons[11] = new Button("×");
        buttons[12] = new Button("4");
        buttons[13] = new Button("5");
        buttons[14] = new Button("6");
        buttons[15] = new Button("-");
        buttons[16] = new Button("3");
        buttons[17] = new Button("2");
        buttons[18] = new Button("1");
        buttons[19] = new Button("+");
        buttons[20] = new Button("±");
        buttons[21] = new Button("0");
        buttons[22] = new Button(".");
        buttons[23] = new Button("=");

        for (Button button : buttons) {
            button.getStyleClass().add("custom-button");
        }



        return buttons;
    }
}
