package org.example.exaption;

public class RecordNotUpdatedException extends RuntimeException{
    public RecordNotUpdatedException(String message) {
        super(message);
    }
}
