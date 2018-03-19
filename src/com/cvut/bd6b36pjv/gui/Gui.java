package com.cvut.bd6b36pjv.gui;

import com.cvut.bd6b36pjv.calculator.api.IGuiCalculator;
import com.cvut.bd6b36pjv.calculator.domain.enumeration.CalculatorOperation;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

public class Gui extends Application {
    public static final CountDownLatch latch = new CountDownLatch(1);
    private static Label digitContainer;
    public static IGuiCalculator calculator;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        latch.countDown();

        primaryStage.setTitle("Calculator");
        stage = primaryStage;

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

    /**
     * Helper to map operation on key pressed
     * @param character Key pressed as character (as string)
     * @throws WrongOperationException WrongOperationException
     */
    private static void mapOperation(String character) throws WrongOperationException {
        switch (character) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                updateView(calculator.input(Integer.parseInt(character)));
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "=":
            case ".":
            case "C":
                updateView(calculator.setOperation(character));
                break;
            case "с":
                updateView(calculator.setOperation(CalculatorOperation.FLUSH));
                break;
            case "E":
            case "e":
                updateView(calculator.setOperation(CalculatorOperation.ERASE));
                break;
            case "s":
            case "S":
                updateView(calculator.setOperation(CalculatorOperation.SQUARE_ROOT));
                break;
            case "q":
            case "Q":
                updateView(calculator.setOperation(CalculatorOperation.SQUARE));
                break;
            case "f":
            case "F":
                updateView(calculator.setOperation(CalculatorOperation.ONE_DIVIDED));
                break;
            case "i":
            case "I":
                updateView(calculator.setOperation(CalculatorOperation.SIGN_SWITCH));
                break;
            case "p":
            case "P":
                updateView(calculator.setOperation(CalculatorOperation.PERCENTAGE));
                break;
            default:
                break;
        }

    }

    /**
     * Helper for creating buttons for gui
     * @return Array of buttons
     */
    private static Button[] createButtons() {
        Button[] buttons = new Button[24];

        buttons[0] = createOperation(CalculatorOperation.PERCENTAGE);
        buttons[1] = createOperation(CalculatorOperation.SQUARE_ROOT);
        buttons[2] = createOperation(CalculatorOperation.SQUARE);
        buttons[3] = createOperation(CalculatorOperation.ONE_DIVIDED);
        buttons[4] = createOperation(CalculatorOperation.ERASE);

        buttons[5] = createOperation(CalculatorOperation.FLUSH);

        buttons[6] = createOperation(CalculatorOperation.REMOVE_LAST);

        buttons[7] = createOperation(CalculatorOperation.DIVISION);
        buttons[8] = createDigit(7);
        buttons[9] = createDigit(8);
        buttons[10] = createDigit(9);
        buttons[11] = createOperation(CalculatorOperation.MULTIPLICATION);
        buttons[12] = createDigit(4);
        buttons[13] = createDigit(5);
        buttons[14] = createDigit(6);
        buttons[15] = createOperation(CalculatorOperation.ADDITION);
        buttons[16] = createDigit(3);
        buttons[17] = createDigit(2);
        buttons[18] = createDigit(1);
        buttons[19] = createOperation(CalculatorOperation.SUBTRACTION);
        buttons[20] = createOperation(CalculatorOperation.SIGN_SWITCH);
        buttons[21] = createDigit(0);
        buttons[22] = createOperation(CalculatorOperation.FLOAT);
        buttons[23] = createOperation(CalculatorOperation.RESULT);

        for (Button button : buttons) {
            button.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent e) {
                    try {
                        if (e.getCode() == KeyCode.ENTER) {
                            updateView(calculator.setOperation(CalculatorOperation.RESULT));
                        } else if (e.getCode() == KeyCode.ESCAPE) {
                            stage.close();
                        } else if (e.getCode() == KeyCode.BACK_SPACE) {
                            updateView(calculator.setOperation(CalculatorOperation.REMOVE_LAST));
                        } else {
                            mapOperation(e.getText());
                        }
                    } catch (WrongOperationException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return buttons;
    }

    /**
     * Digit button creation
     *
     * @param digit Digit
     * @return Styled button with event handler
     */
    private static Button createDigit(int digit) {
        Button b = new Button(String.valueOf(digit));
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updateView(calculator.input(digit));
            }
        });
        b.getStyleClass().add("custom-button");
        return b;
    }

    /**
     * Operation button creation
     *
     * @param operation Operation
     * @return Styled button with event handler
     */
    private static Button createOperation(String operation) {
        Button b = new Button(String.valueOf(operation));
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    updateView(calculator.setOperation(operation));
                } catch (WrongOperationException e) {
                    e.printStackTrace();
                }
            }
        });
        b.getStyleClass().add("custom-button");
        return b;
    }

    /**
     * Update view label with current operand
     *
     * @param operand Number to display
     */
    private static void updateView(String operand) {
        digitContainer.setText(operand);
    }
}
