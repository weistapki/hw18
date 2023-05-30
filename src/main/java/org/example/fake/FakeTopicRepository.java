package org.example.fake;

import org.example.dao.TopicRepository;
import org.example.model.Topic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FakeTopicRepository implements TopicRepository {
    private final Map<Integer, Topic> topicMap;

    public FakeTopicRepository() {
        this.topicMap = new HashMap<>();
    }
    @Override
    public boolean save(Topic topic) {
        if (topicMap.containsKey(topic.getId())) {
            return false; // Topic with the same ID already exists
        }
        topicMap.put(topic.getId(), topic);
        return true;
    }
    @Override
    public Topic get(int id) {
        return topicMap.get(id);
    }
    @Override
    public boolean remove(int id) {
        return topicMap.remove(id) != null;
    }
    @Override
    public boolean update(Topic topic) {
        if (!topicMap.containsKey(topic.getId())) {
            return false; // Topic doesn't exist
        }
        topicMap.put(topic.getId(), topic);
        return true;
    }
    @Override
    public List<Topic> getAll() {
        return new ArrayList<>(topicMap.values());
    }
}

