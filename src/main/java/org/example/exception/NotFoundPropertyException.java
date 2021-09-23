package org.example.exception;

public class NotFoundPropertyException extends ParserException {
    public NotFoundPropertyException() {
        super("Property is not found.");
    }

    public NotFoundPropertyException(String message) {
        super("Property is not found: " + message);
    }
}
