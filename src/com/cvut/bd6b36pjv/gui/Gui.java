package com.cvut.bd6b36pjv.gui;

import com.cvut.bd6b36pjv.calculator.api.ICalculator;
import com.cvut.bd6b36pjv.calculator.api.IGuiCalculator;
import com.cvut.bd6b36pjv.calculator.api.IReal;
import com.cvut.bd6b36pjv.calculator.domain.CalculatorOperation;
import com.cvut.bd6b36pjv.calculator.domain.Operation;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

public class Gui extends Application {
    public static final CountDownLatch latch = new CountDownLatch(1);
    private static Label digitContainer;

    public static IGuiCalculator calculator;

    @Override
    public void start(Stage primaryStage) throws Exception {
        latch.countDown();

        primaryStage.setTitle("Calculator");


        GridPane root = new GridPane();
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(20);
        root.getRowConstraints().add(row);

        GridPane buttonsPanel = new GridPane();

        for (int i = 0; i < 4; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(25);
            buttonsPanel.getColumnConstraints().add(col);
        }

        digitContainer = new Label("0");
        digitContainer.getStyleClass().add("digits-label");

        AnchorPane.setRightAnchor(digitContainer, 0.0);
        digitContainer.setAlignment(Pos.CENTER_RIGHT);

        Button[] buttons = createButtons();

        for (int i = 0; i < buttons.length; i++) {
            buttonsPanel.add(buttons[i], i % 4, i / 4);
        }

        root.add(digitContainer, 0, 0);
        root.add(buttonsPanel, 0, 1);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        scene.getStylesheets().add("com/cvut/bd6b36pjv/gui/styles/styles.css");

        primaryStage.show();
    }

    private static Button[] createButtons() {
        Button[] buttons = new Button[24];

        buttons[0] = new Button("%");
        buttons[1] = new Button("√");
        buttons[2] = new Button("x^2");
        buttons[3] = new Button("1/x");
        buttons[4] = new Button("CE");

        buttons[5] = new Button("C");

        buttons[6] = new Button("<");
        buttons[6].setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                IReal operand = calculator.getCurrentOperand();
                operand.removeDigit();
                digitContainer.setText(operand.stringValue(operand.precision()));
            }
        });
        buttons[7] = createBinaryOperation(CalculatorOperation.DIVISION, calculator, digitContainer);
        buttons[8] = createDigit(7, calculator, digitContainer);
        buttons[9] = createDigit(8, calculator, digitContainer);
        buttons[10] = createDigit(9, calculator, digitContainer);
        buttons[11] = createBinaryOperation(CalculatorOperation.MULTIPLICATION, calculator, digitContainer);
        buttons[12] = createDigit(4, calculator, digitContainer);
        buttons[13] = createDigit(5, calculator, digitContainer);
        buttons[14] = createDigit(6, calculator, digitContainer);
        buttons[15] = createBinaryOperation(CalculatorOperation.ADDITION, calculator, digitContainer);
        buttons[16] = createDigit(3, calculator, digitContainer);
        buttons[17] = createDigit(2, calculator, digitContainer);
        buttons[18] = createDigit(1, calculator, digitContainer);
        buttons[19] = createBinaryOperation(CalculatorOperation.SUBTRACTION, calculator, digitContainer);
        buttons[20] = new Button("±");
        buttons[21] = createDigit(0, calculator, digitContainer);
        buttons[22] = new Button(".");
        buttons[23] = new Button("=");
        buttons[23].setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    IReal operand = calculator.compute();
                    digitContainer.setText(operand.stringValue(operand.precision()));
                } catch (WrongOperationException e) {
                    e.printStackTrace();
                }
            }
        });


        for (Button button : buttons) {
            button.getStyleClass().add("custom-button");
        }

        return buttons;
    }

    /**
     * On digit pressed handler
     *
     * @param calc  Calculator
     * @param digit Digit pressed
     * @return Event handler
     */
    private static EventHandler<ActionEvent> onDigitPressed(IGuiCalculator calc, int digit, Label digitContainer) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                IReal operand = calc.getCurrentOperand();
                operand.appendDigit(digit);
                digitContainer.setText(operand.stringValue(operand.precision()));
            }
        };
    }

    /**
     * On binary operation pressed handler
     *
     * @param calc  Calculator
     * @param operation Operation pressed
     * @return Event handler
     */
    private static EventHandler<ActionEvent> onBinaryOperationPressed(IGuiCalculator calc, String operation, Label digitContainer) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calc.pushOperand();
                calc.setOperation(operation);
                IReal operand = calc.getCurrentOperand();
                digitContainer.setText(operand.stringValue(operand.precision()));
            }
        };
    }

    private static Button createDigit(int digit, IGuiCalculator calc, Label digitContainer) {
        Button b = new Button(String.valueOf(digit));
        b.setOnAction(onDigitPressed(calc, digit, digitContainer));
        b.getStyleClass().add("custom-button");
        return b;
    }


    private static Button createBinaryOperation(String operation, IGuiCalculator calc, Label digitContainer) {
        Button b = new Button(String.valueOf(operation));
        b.setOnAction(onBinaryOperationPressed(calc, operation, digitContainer));
        b.getStyleClass().add("custom-button");
        return b;
    }


}
