package com.matanalbert.assignment5.util;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

public class Logger {
    private PrintStream log;

    public Logger(String logFileName) {
        try {
            log = new PrintStream(logFileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening log file. Logging is disabled.");
            log = null;
        }
    }

    public void logException(Exception e) {
        if (log == null) {
            return;
        }
        log.println(new Date().toInstant().toString());
        e.printStackTrace(log);
        log.flush();
    }
}
