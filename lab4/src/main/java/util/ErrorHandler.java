package main.java.util;

public class ErrorHandler {
    public static void logError(String message, Throwable e) {
        System.err.println(message);
        e.printStackTrace();
    }
}
