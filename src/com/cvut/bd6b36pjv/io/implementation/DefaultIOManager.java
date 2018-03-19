package com.cvut.bd6b36pjv.io.implementation;

import com.cvut.bd6b36pjv.calculator.domain.enumeration.BasicCalculatorOperation;
import com.cvut.bd6b36pjv.exceptions.DivisionByZeroException;
import com.cvut.bd6b36pjv.exceptions.WrongOperandException;
import com.cvut.bd6b36pjv.exceptions.WrongOperationException;
import com.cvut.bd6b36pjv.io.api.IOManager;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.cvut.bd6b36pjv.calculator.domain.enumeration.BasicCalculatorOperation.*;

/**
 * @see IOManager basic implementation
 */
public class DefaultIOManager implements IOManager {
    //--- Static fields ---//

    /**
     * Console output strings - defaults
     */
    private static Map<String, String> textsDefault;

    // initialization
    static {
        textsDefault = new HashMap<String, String>();
        textsDefault.put("operation", "Vyber operaci (1-soucet, 2-rozdil, 3-soucin, 4-podil, q-ukoncit):");

        textsDefault.put("operand", "Zadej operand: ");

        textsDefault.put("+", "Zadej scitanec: ");
        textsDefault.put("+0", "Zadej scitanec: ");
        textsDefault.put("+1", "Zadej scitanec: ");

        textsDefault.put("-", "Zadej mensenec: ");
        textsDefault.put("-0", "Zadej mensenec: ");
        textsDefault.put("-1", "Zadej mensitel: ");

        textsDefault.put("*", "Zadej cinitel: ");
        textsDefault.put("*0", "Zadej cinitel: ");
        textsDefault.put("*1", "Zadej cinitel: ");

        textsDefault.put("/", "Zadej delenec: ");
        textsDefault.put("/0", "Zadej delenec: ");
        textsDefault.put("/1", "Zadej delitel: ");

        textsDefault.put(".", "Zadej pocet desetinnych mist: ");

        textsDefault.put("errorOperation", "Chybna volba!");
        textsDefault.put("errorNAN", "Chyba - musi byt zadane realne cislo!");
        textsDefault.put("error/0", "Pokus o deleni nulou!");
        textsDefault.put("error.", "Chyba - musi byt zadane cele nezaporne cislo!");
    }

    //--- Private fields ---//

    /**
     * Console output strings
     */
    private Map<String, String> texts;

    /**
     * Input scanner
     */
    private Scanner in;

    /**
     * Output print stream
     */
    private PrintStream out;

    //--- Implementation ---//
    @Override
    public String readOperation() throws WrongOperationException, IllegalAccessException {
        out.print(this.texts.get("operation"));
        String input = in.next();
        if (!BasicCalculatorOperation.Contains(BasicCalculatorOperation.class, input)) {
            out.println(this.texts.get("errorOperation"));
            throw new WrongOperationException();
        }
        return BasicCalculatorOperation.Code(BasicCalculatorOperation.class, input);
    }

    @Override
    public BigDecimal[] readOperands() throws WrongOperandException {
        BigDecimal[] operands = new BigDecimal[2];
        out.print(this.texts.get("operand"));
        operands[0] = this.parseBigDecimal(in.next());
        out.print(this.texts.get("operand"));
        operands[1] = this.parseBigDecimal(in.next());
        return operands;
    }

    @Override
    public BigDecimal[] readOperands(String op) throws WrongOperandException, WrongOperationException {
        BigDecimal[] operands = new BigDecimal[2];
        switch (op) {
            case ADDITION:
                out.print(this.texts.get("+"));
                operands[0] = this.parseBigDecimal(in.next());
                out.print(this.texts.get("+"));
                operands[1] = this.parseBigDecimal(in.next());
                return operands;
            case SUBTRACTION:
                out.print(this.texts.get("-0"));
                operands[0] = this.parseBigDecimal(in.next());
                out.print(this.texts.get("-1"));
                operands[1] = this.parseBigDecimal(in.next());
                return operands;
            case MULTIPLICATION:
                out.print(this.texts.get("*0"));
                operands[0] = this.parseBigDecimal(in.next());
                out.print(this.texts.get("*1"));
                operands[1] = this.parseBigDecimal(in.next());
                return operands;
            case DIVISION:
                out.print(this.texts.get("/0"));
                operands[0] = this.parseBigDecimal(in.next());
                out.print(this.texts.get("/1"));
                operands[1] = this.parseBigDecimal(in.next());
                if (Double.compare(operands[1].doubleValue(), 0.0) == 0) {
                    out.print(this.texts.get("error/0"));
                    throw new DivisionByZeroException();
                }
                return operands;
            default:
                throw new WrongOperationException();
        }
    }

    @Override
    public int readPrecision() throws WrongOperandException {
        out.print(this.texts.get("."));
        return this.parsePrecision(in.next());
    }

    @Override
    public void printResult(BigDecimal[] operands, String op, BigDecimal result, int precision) {
        // TODO: optimise precision
        out.printf("%s %s %s = %s\n\n", operands[0].toPlainString(), op, operands[1].toPlainString(), result.toPlainString());
    }

    @Override
    public void errorMessage(String s) {
        this.out.println(this.texts.get(s));
    }

    //--- Constructors ---//

    /**
     * New IOManager instance
     */
    public DefaultIOManager() {
        this(System.in, System.out);
    }

    /**
     * IOManager with custom streams
     *
     * @param in  Input stream
     * @param out Output stream
     */
    public DefaultIOManager(InputStream in, PrintStream out) {
        this(in, out, textsDefault);
    }

    /**
     * IOManager with custom streams and console output strings
     *
     * @param in    Input stream
     * @param out   Output stream
     * @param texts Console output strings
     */
    public DefaultIOManager(InputStream in, PrintStream out, Map<String, String> texts) {
        this.in = new Scanner(in);
        this.out = out;
        this.texts = texts;
    }

    //--- Helpers ---//

    /**
     * Tries to parse string for a real number value
     *
     * @param str String to be parsed
     * @return BigDecimal number
     * @throws WrongOperandException See {@see WrongOperandException}
     */
    private BigDecimal parseBigDecimal(String str) throws WrongOperandException {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            out.println(this.texts.get("errorNAN"));
            throw new WrongOperandException();
        }
    }

    /**
     * Tries to parse string for an unsigned integer value
     *
     * @param str String to be parsed
     * @return Precision as an unsigned integer
     * @throws WrongOperandException See {@see WrongOperandException}
     */
    private int parsePrecision(String str) throws WrongOperandException {
        try {
            int i = Integer.parseInt(str);
            if (i < 0) {
                out.println(this.texts.get("error."));
                throw new WrongOperandException();
            }
            return i;
        } catch (NumberFormatException e) {
            out.println(this.texts.get("error."));
            throw new WrongOperandException();
        }
    }
}

