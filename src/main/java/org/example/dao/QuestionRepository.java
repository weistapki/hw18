package org.example.dao;

import org.example.model.Question;

import java.util.List;


public interface QuestionRepository {
    Question save(Question question) ;
    Question get(int id) ;
    List<Question> getAllByTopic() ;
    boolean remove(int id) ;
    boolean update(Question question) ;
}
