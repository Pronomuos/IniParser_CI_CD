package org.example.exception;

public class NotFoundSectionException extends ParserException {
    public NotFoundSectionException() {
        super("Section not found");
    }

    public NotFoundSectionException(String name) {
        super("Section with name " + name + " not found");
    }
}
