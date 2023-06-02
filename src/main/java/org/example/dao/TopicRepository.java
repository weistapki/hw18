package org.example.dao;

import org.example.exaption.IdNotFoundException;
import org.example.model.Question;
import org.example.model.Topic;

import java.util.List;


public interface TopicRepository {
    Topic save(Topic topic) ;
    Topic get(int id) ;
    List<Topic> getAll() ;
    boolean remove(int id) ;
    boolean update(Topic topic) ;
}
