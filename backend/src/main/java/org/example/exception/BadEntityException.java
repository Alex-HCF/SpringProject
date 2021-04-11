package org.example.exception;

public class BadEntityException extends IllegalArgumentException{
    public BadEntityException() {
        super();
    }

    public BadEntityException(String s) {
        super(s);
    }

    public BadEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadEntityException(Throwable cause) {
        super(cause);
    }
}
