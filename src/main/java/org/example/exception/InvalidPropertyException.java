package org.example.exception;

public class InvalidPropertyException extends ParserException {
    public InvalidPropertyException() {
        super("Property is invalid.");
    }

    public InvalidPropertyException(String message) {
        super("Property is invalid: " + message);
    }
}
