package com.matanalbert.assignment4.exception;

import java.util.Scanner;

public class AutoException extends Exception {
    public static int INVALID_FILE = 1;
    public static int MISSING_STRING_VALUE = 2;
    public static int MISSING_FLOAT_VALUE = 3;
    public static final int MISSING_INT_VALUE = 4;

    private int errorNumber;
    private String errorMessage;
    private final Scanner scanner = new Scanner(System.in);

    public AutoException() {
    }

    public AutoException(int errorNumber, String errorMessage) {
        super(String.format("%s (%d)", errorMessage, errorNumber));
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Fixes an invalid file exception
     * @return user-inputted file name
     */
    public String fix1() {
        System.out.print("Please enter a valid file name: ");
        return scanner.next();
    }

    /**
     * Fixes a missing String exception
     * @param name the missing property
     * @return user-inputted String
     */
    public String fix2(String name) {
        System.out.println(errorMessage);
        while (true) {
            System.out.print("Please enter a valid " + name + ": ");
            String value = scanner.nextLine();
            if (!value.isBlank()) {
                return value;
            }
        }
    }

    /**
     * Fixes a missing float exception
     * @param name the missing property
     * @return user-inputted float
     */
    public float fix3(String name) {
        System.out.println(errorMessage);
        while (true) {
            System.out.print("Please enter a valid " + name + ": ");
            if (scanner.hasNextFloat()) {
                return scanner.nextFloat();
            }
            scanner.next();
        }
    }

    /**
     * Fixes a missing int exception
     * @param name the missing property
     * @return user-inputted int
     */
    public int fix4(String name) {
        System.out.println(errorMessage);
        while (true) {
            System.out.print("Please enter a valid " + name + ": ");
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value < 0) {
                    continue;
                }
                else {
                    return value;
                }
            }
            scanner.next();
        }
    }
}
