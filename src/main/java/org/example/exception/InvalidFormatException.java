package org.example.exception;

public class InvalidFormatException extends ParserException {
    public InvalidFormatException() {
        super("Format of the ini file is invalid.");
    }

    public InvalidFormatException(String message) {
        super("Format of the ini file is invalid: " + message);
    }
}
