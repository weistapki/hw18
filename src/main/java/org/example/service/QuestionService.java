package org.example.service;

import org.example.dao.QuestionRepository;
import org.example.dao.TopicRepository;
import org.example.exaption.*;
import org.example.model.Question;
import org.example.model.Topic;

import java.util.Collections;
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
    public Question getRandomQuestionByTopic() {
        try {
            List<Question> questions = questionRepository.getAllByTopic();
            if (questions.isEmpty()) {
                return null;
            }
            int randomIndex = ThreadLocalRandom.current().nextInt(questions.size());
            return questions.get(randomIndex);
        } catch (QuestionNotFoundException e) {
            System.out.println("Error occurred while getting random question by topic: " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets a random question from all questions.
     * If there are no questions, returns null.
     */
    public Question getRandomQuestion() {
        try {
            List<Question> questions = questionRepository.getAllByTopic();
            if (questions.isEmpty()) {
                return null;
            }
            int randomIndex = ThreadLocalRandom.current().nextInt(questions.size());
            return questions.get(randomIndex);
        } catch (QuestionListEmptyException e) {
            System.out.println("Error occurred while getting random question: " + e.getMessage());
            return null;
        }
    }


    /**
     * Adds a new question.
     */
    public void addQuestion(Question question) {
        try {
            questionRepository.save(question);
        } catch (QuestionNotFoundException e) {
            System.out.println("Error occurred while adding a question: " + e.getMessage());
        }
    }

    /**
     * Deletes a question by its ID.
     */
    public void removeQuestion(int questionId) throws RecordsNotDeletedException {
        try {
            questionRepository.remove(questionId);
        } catch (RecordsNotDeletedException e) {
            System.out.println("Error occurred while removing question with ID: " + questionId + ": " + e.getMessage());
            throw new RecordsNotDeletedException(e.getMessage());
        }
    }

    /**
     * Gets a list of all topics.
     */
    public List<Topic> getTopics() {
        try {
            return topicRepository.getAll();
        } catch (TopicsNotRetrievedException e) {
            System.out.println("Error occurred while getting topics: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Saves a topic.
     */
    public void saveTopic(Topic topic) {
        try {
            topicRepository.save(topic);
        } catch (ObjectNotSavedException e) {
            System.out.println("Error occurred while saving a topic: " + e.getMessage());
        }
    }

    /**
     * Gets a list of all questions.
     */
    public List<Question> getAllQuestions() {
        try {
            return questionRepository.getAllByTopic();
        } catch (QuestionsNotRetrievedException e) {
            System.out.println("Error occurred while getting all questions: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}


