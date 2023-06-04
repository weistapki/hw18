package org.example.exaption;

public class ObjectNotSavedException extends RuntimeException{
    public ObjectNotSavedException(String message) {
        super(message);
    }
}
