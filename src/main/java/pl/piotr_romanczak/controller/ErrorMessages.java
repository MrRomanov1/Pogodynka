package pl.piotr_romanczak.controller;

public class ErrorMessages {

    private static String ERROR_MESSAGE;

    public static void setErrorMessage(String errorMessage) {
        ERROR_MESSAGE = errorMessage;
    }

    public static String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
