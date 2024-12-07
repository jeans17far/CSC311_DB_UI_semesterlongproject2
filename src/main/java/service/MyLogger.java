package service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for logging messages.
 */
public class MyLogger {

    /** Global logger instance. */
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Logs an information message.
     * @param msg Message to log.
     */
    public static void makeLog(String msg) {
        LOGGER.log(Level.INFO, "CSC311_Log__ " + msg);
    }
}
