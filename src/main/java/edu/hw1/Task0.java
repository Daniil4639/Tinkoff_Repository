package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task0 {

    private Task0() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void helloOutput() {
        LOGGER.info("Hello, World!");
    }

}
