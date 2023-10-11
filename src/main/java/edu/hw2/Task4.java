package edu.hw2;

public class Task4 {

    private Task4() {
    }

    public static CallingInfo callingInfo() {

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        return new CallingInfo(
            stackTraceElements[2].getClassName(),
            stackTraceElements[2].getMethodName()
        );
    }

    public record CallingInfo(String className, String methodName) {
    }
}
