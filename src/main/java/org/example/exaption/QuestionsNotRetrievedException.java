package org.example.exaption;

public class QuestionsNotRetrievedException extends RuntimeException{
    public QuestionsNotRetrievedException(String message) {
        super(message);
    }
}
