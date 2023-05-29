package org.example.service;

import org.example.dao.QuestionRepository;
import org.example.dao.TopicRepository;
import org.example.model.Question;
import org.example.model.Topic;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class QuestionService {
    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;

    public QuestionService(TopicRepository topicRepository, QuestionRepository questionRepository) {
        this.topicRepository = topicRepository;
        this.questionRepository = questionRepository;
    }

    /**
     * Gets a random question on the specified topic.
     * If there are no questions on the topic, returns null.
     */
    public Question getRandomQuestionByTopic(int topicId) {
        List<Question> questions = questionRepository.getAllByTopic();
        if (questions.isEmpty()) {
            return null;
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(questions.size());
        return questions.get(randomIndex);
    }

    /**
     * Gets a random question from all questions.
     * If there are no questions, returns null.
     */
    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.getAllByTopic();
        if (questions.isEmpty()) {
            return null;
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(questions.size());
        return questions.get(randomIndex);
    }

    /**
     * Adds a new question.
     */
    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    /**
     * Deletes a question by its ID.
     */
    public void removeQuestion(int questionId) {
        questionRepository.remove(questionId);
    }

    /**
     * Gets a list of all topics.
     */
    public List<Topic> getTopics() {
        return topicRepository.getAll();
    }

    /**
     * Gets a list of all topics.
     */
    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }

    /**
     * Gets a list of all questions.
     */
    public List<Question> getAllQuestions() {
        return questionRepository.getAllByTopic();
    }
}

