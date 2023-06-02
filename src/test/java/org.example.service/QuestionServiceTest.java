package org.example.service;


import org.example.dao.QuestionRepository;
import org.example.dao.TopicRepository;
import org.example.exaption.IdNotFoundException;
import org.example.fake.FakeQuestionRepository;
import org.example.fake.FakeTopicRepository;
import org.example.model.Question;
import org.example.model.Topic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.Timestamp;

import java.util.List;

public class QuestionServiceTest {
    private QuestionRepository questionRepository;
    private TopicRepository topicRepository;
    private QuestionService questionService;

    @Before
    public void setUp() {
        questionRepository = new FakeQuestionRepository();
        topicRepository = new FakeTopicRepository();
        questionService = new QuestionService(topicRepository, questionRepository);
    }

    @Test
    public void testGetRandomQuestionByTopic() throws IdNotFoundException {
        // Create a test topic
        Topic testTopic = new Topic(1, "Technology", "Latest trends in the tech industry", new Timestamp(System.currentTimeMillis()));
        topicRepository.save(testTopic);

        // Create test questions and associate them with the topic
        Question testQuestion1 = new Question(1, "What is artificial intelligence?", 1, new Timestamp(System.currentTimeMillis()));
        Question testQuestion2 = new Question(2, "How does blockchain work?", 1, new Timestamp(System.currentTimeMillis()));
        Question testQuestion3 = new Question(3, "What are the benefits of cloud computing?", 1, new Timestamp(System.currentTimeMillis()));
        Question testQuestion4 = new Question(4, "What is the Internet of Things (IoT)?", 1, new Timestamp(System.currentTimeMillis()));
        questionRepository.save(testQuestion1);
        questionRepository.save(testQuestion2);
        questionRepository.save(testQuestion3);
        questionRepository.save(testQuestion4);

        // Get a random question on the specified topic
        Question randomQuestion = questionService.getRandomQuestionByTopic();

        // Check that the retrieved question is not null and has the correct topic
        Assert.assertNotNull(randomQuestion);
        Assert.assertEquals(1, randomQuestion.getTopicId());

        // Clean up test data
        questionRepository.remove(1);
        questionRepository.remove(2);
        questionRepository.remove(3);
        questionRepository.remove(4);
        topicRepository.remove(1);

        // Print the random question to the console
        System.out.println("Random question by topic:");
        System.out.println(randomQuestion);
    }

    @Test
    public void testGetRandomQuestion() throws IdNotFoundException {
        // Create test questions
        Question testQuestion1 = new Question(1, "What is the capital of France?", 1, new Timestamp(System.currentTimeMillis()));
        Question testQuestion2 = new Question(2, "Who painted the Mona Lisa?", 2, new Timestamp(System.currentTimeMillis()));
        Question testQuestion3 = new Question(3, "What is the boiling point of water?", 3, new Timestamp(System.currentTimeMillis()));
        Question testQuestion4 = new Question(4, "Who wrote the play Romeo and Juliet?", 4, new Timestamp(System.currentTimeMillis()));
        questionRepository.save(testQuestion1);
        questionRepository.save(testQuestion2);
        questionRepository.save(testQuestion3);
        questionRepository.save(testQuestion4);

        // Get a random question from all questions
        Question randomQuestion = questionService.getRandomQuestion();

        // Check that the retrieved question is not null
        Assert.assertNotNull(randomQuestion);

        // Clean up test data
        questionRepository.remove(1);
        questionRepository.remove(2);
        questionRepository.remove(3);
        questionRepository.remove(4);

        // Print the random question to the console
        System.out.println("Random question from all questions:");
        System.out.println(randomQuestion);
    }

    @Test
    public void testAddQuestion() throws IdNotFoundException {
        // Create a test question
        Question testQuestion = new Question(1, "What is your favorite color?", 1, new Timestamp(System.currentTimeMillis()));

        // Add the question
        questionService.addQuestion(testQuestion);

        // Get the question by ID
        Question addedQuestion = questionRepository.get(1);

        // Check that the retrieved question is not null and has the correct data
        Assert.assertNotNull(addedQuestion);
        Assert.assertEquals("What is your favorite color?", addedQuestion.getText());

        // Clean up test data
        questionRepository.remove(1);

        // Print the added question to the console
        System.out.println("Added question:");
        System.out.println(addedQuestion);
    }

    @Test
    public void testRemoveQuestion() throws IdNotFoundException {
        // Create a test question
        Question testQuestion = new Question(1, "What is the capital of Germany?", 1, new Timestamp(System.currentTimeMillis()));
        questionRepository.save(testQuestion);

        // Remove the question
        questionService.removeQuestion(1);

        // Check that the question is removed
        Question removedQuestion = questionRepository.get(1);
        boolean questionRemoved = (removedQuestion == null);

        // Print the removal status to the console
        String removalStatus = questionRemoved ? "Question removed successfully" : "Question removal failed";
        System.out.println("Question removal status: " + removalStatus);
    }

    @Test
    public void testGetTopics() throws IdNotFoundException {
        // Create test topics
        Topic topic1 = new Topic(1, "Science", "Exploring the natural world", new Timestamp(System.currentTimeMillis()));
        Topic topic2 = new Topic(2, "History", "Studying the past events", new Timestamp(System.currentTimeMillis()));
        topicRepository.save(topic1);
        topicRepository.save(topic2);

        // Get a list of all topics
        List<Topic> topics = questionService.getTopics();

        // Check that the list is not null and contains the correct number of topics
        Assert.assertNotNull(topics);
        Assert.assertEquals(2, topics.size());

        // Clean up test data
        topicRepository.remove(1);
        topicRepository.remove(2);

        // Print the topics to the console
        System.out.println("Topics:");
        for (Topic topic : topics) {
            System.out.println(topic);
        }
    }

    @Test
    public void testSaveTopic() throws IdNotFoundException {
        // Create a test topic
        Topic testTopic = new Topic(1, "Sports", "Various sports disciplines", new Timestamp(System.currentTimeMillis()));

        // Save the topic
        questionService.saveTopic(testTopic);

        // Get the topic by ID
        Topic savedTopic = topicRepository.get(1);

        // Check that the retrieved topic is not null and has the correct data
        Assert.assertNotNull(savedTopic);
        Assert.assertEquals("Sports", savedTopic.getName());

        // Clean up test data
        topicRepository.remove(1);

        // Print the saved topic to the console
        System.out.println("Saved topic:");
        System.out.println(savedTopic);
    }

    @Test
    public void testGetAllQuestions() throws IdNotFoundException {
        // Create test questions
        Question question1 = new Question(1, "What is the capital of Spain?", 1, new Timestamp(System.currentTimeMillis()));
        Question question2 = new Question(2, "Who is the author of Harry Potter?", 2, new Timestamp(System.currentTimeMillis()));
        Question question3 = new Question(3, "What is the largest planet in the solar system?", 3, new Timestamp(System.currentTimeMillis()));
        Question question4 = new Question(4, "Who discovered electricity?", 4, new Timestamp(System.currentTimeMillis()));
        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        questionRepository.save(question4);

        // Get a list of all questions
        List<Question> questions = questionService.getAllQuestions();

        // Check that the list is not null and contains the correct number of questions
        Assert.assertNotNull(questions);
        Assert.assertEquals(4, questions.size());

        // Clean up test data
        questionRepository.remove(1);
        questionRepository.remove(2);
        questionRepository.remove(3);
        questionRepository.remove(4);

        // Print the questions to the console
        System.out.println("All questions:");
        for (Question question : questions) {
            System.out.println(question);
        }
    }
}
