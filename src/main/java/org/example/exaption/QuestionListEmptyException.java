package org.example.exaption;

public class QuestionListEmptyException extends RuntimeException{
    public QuestionListEmptyException(String message) {
        super(message);
    }
}
