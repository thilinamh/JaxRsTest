package com.epic.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by thilina_h on 9/15/2017.
 */
public class DBException extends ExceptionBase {
    private static int httpCode = 500;
    private static String ecode = "I/E";//"Internal Error";
     Logger debugLogger = LoggerFactory.getLogger(DBException.class);

    public DBException(String message, Throwable cause, int httpCode, String ecode) {
        super(message, cause, httpCode, ecode);
    }

    public DBException(String message, Throwable cause) {
        this(message, cause, httpCode, ecode);
        debugLogger.warn(message, cause);
    }

    public DBException(String message) {
        this(message, null, httpCode, ecode);
        debugLogger.warn(message);
    }
}