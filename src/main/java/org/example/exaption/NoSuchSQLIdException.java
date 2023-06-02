package org.example.exaption;

public class NoSuchSQLIdException extends RuntimeException {
    public NoSuchSQLIdException(String message) {
        super(message);
    }
}
