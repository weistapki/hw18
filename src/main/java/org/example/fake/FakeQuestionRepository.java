package org.example.fake;

import org.example.dao.QuestionRepository;
import org.example.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeQuestionRepository implements QuestionRepository {
    private final Map<Integer, Question> questionMap;

    public FakeQuestionRepository() {
        this.questionMap = new HashMap<>();
    }
    @Override
    public boolean save(Question question) {
        if (questionMap.containsKey(question.getId())) {
            return false; // Question with the same ID already exists
        }
        questionMap.put(question.getId(), question);
        return true;
    }
    @Override
    public Question get(int id) {
        return questionMap.get(id);
    }
    @Override
    public boolean remove(int id) {
        return questionMap.remove(id) != null;
    }
    @Override
    public boolean update(Question question) {
        if (!questionMap.containsKey(question.getId())) {
            return false; // Question doesn't exist
        }
        questionMap.put(question.getId(), question);
        return true;
    }
    @Override
    public List<Question> getAllByTopic() {
        return new ArrayList<>(questionMap.values());
    }
}

