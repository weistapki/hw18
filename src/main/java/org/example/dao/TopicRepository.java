package org.example.dao;

import org.example.model.Topic;



public interface TopicRepository {
    boolean save(Topic topic);

    Topic get(int id);

    boolean remove(int id);

    boolean update(Topic topic);
}
