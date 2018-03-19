package com.cvut.bd6b36pjv.calculator.domain.enumeration;

import com.cvut.bd6b36pjv.exceptions.WrongOperationException;

import java.lang.reflect.Field;

/**
 * Global program operations
 */
public abstract class Operation {
    public static final String QUIT = "quit";

    /**
     * See if contains such value
     * @param cl Caller class
     * @param code Code to look for
     * @return True if contains, false otherwise
     * @throws IllegalAccessException IllegalAccessException
     */
    public static boolean Contains(Class cl, String code) throws IllegalAccessException {
        Field[] fields = cl.getFields();
        for(Field f:fields) {
            if (code.equals(f.get("")))
                return true;
        }
        return false;
    }

    /**
     * See if contains such operation and return it
     * @param cl Caller class
     * @param code Code to look for
     * @return Operation code
     * @throws IllegalAccessException IllegalAccessException
     * @throws WrongOperationException WrongOperationException
     */
    public static String Code(Class cl, String code) throws IllegalAccessException, WrongOperationException {
        Field[] fields = cl.getFields();
        for(Field f:fields) {
            if (code.equals(f.get("")))
                return f.get("").toString();
        }
        throw new WrongOperationException();

    }
}
