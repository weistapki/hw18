package org.example.dao;

import org.example.model.Question;



public interface QuestionRepository {
    boolean save(Question question);

    Question get(int id);

    boolean remove(int id);

    boolean update(Question question);
}
