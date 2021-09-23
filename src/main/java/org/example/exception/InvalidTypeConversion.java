package org.example.exception;

import org.example.model.AvailableTypes;

public class InvalidTypeConversion extends ParserException {
    public InvalidTypeConversion() {
        super("Type conversion is invalid.");
    }

    public InvalidTypeConversion(String message) {
        super("Type conversion is invalid: " + message);
    }
}
