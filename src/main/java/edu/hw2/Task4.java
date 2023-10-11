package edu.hw2;

import java.io.IOException;

public class Task4 {

    public record CallingInfo(String className, String methodName) {}

    public static CallingInfo callingInfo() {

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return new CallingInfo(stackTraceElements[2].getClassName(),
            stackTraceElements[2].getMethodName());
    }
}
