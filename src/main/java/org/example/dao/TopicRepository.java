package org.example.dao;

import org.example.model.Topic;

import java.util.List;


public interface TopicRepository {
    boolean save(Topic topic);
    Topic get(int id);
    List<Topic> getAll();
    boolean remove(int id);
    boolean update(Topic topic);
}
