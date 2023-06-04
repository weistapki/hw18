package org.example.exaption;

public class RecordsNotDeletedException extends RuntimeException{
    public RecordsNotDeletedException(String message) {
        super(message);
    }
}
