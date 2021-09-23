package org.example.exception;

public class ParserException extends Exception {
    public ParserException() {}

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
