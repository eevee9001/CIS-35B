package com.matanalbert.assignment2.exception;

import java.io.*;


public class AutoException extends Exception {
    private int errorNumber;
    private String errorMessage;

    public AutoException() {
        super();
        printMyException();
    }

    public AutoException(int errorNumber, String errorMessage) {
        super();
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
        printMyException();
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

    public void printMyException() {
        System.out.println("AutoException errorNumber = " + errorNumber + ", errorMessage =" + errorMessage);
        try {
            logExceptions(errorMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logExceptions(String exceptionToLog) throws IOException {
        FileWriter writer;
        try {
            File log = new File("exceptionLog.log");
            if (!log.exists()) {
                log.createNewFile();
            }

            writer = new FileWriter(log, true); {
                writer.write(exceptionToLog + "\n");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    public void fix(int errorNumber) {
        Fix1To100 f1 = new Fix1to100();
        switch (errorNumber) {
            case 1: f1.fix1(errorNumber);
            break;

        }
    }
    */

}
