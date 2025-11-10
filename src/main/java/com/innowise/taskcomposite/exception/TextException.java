package com.innowise.taskcomposite.exception;

public class TextException extends Exception {
    public TextException() {

    }
    public TextException(Throwable e) {
        super(e);
    }
    public TextException(String message) {
        super(message);
    }
    public TextException(String message, Throwable e) {
        super(message, e);
    }
}
