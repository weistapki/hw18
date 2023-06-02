import org.example.exaption.RecordsNotDeletedException;
import org.example.model.Question;
import org.example.model.Topic;
import org.example.service.QuestionService;

import java.util.Scanner;

import java.util.*;



class Shell {
    private QuestionService questionService;
    private Scanner scanner;

    public Shell(QuestionService questionService, Scanner scanner) {
        this.questionService = questionService;
        this.scanner = scanner;
    }

    public void getRandomQuestionByTopic() {
        System.out.println("Enter the topic name: ");
        String topicName = scanner.nextLine();
        Topic topic = new Topic(0, topicName, "", null); // Create a Topic object with a temporary ID and empty description
        Question question = questionService.getRandomQuestionByTopic();
        if (question != null) {
            System.out.println("Question: " + question.getText());
        } else {
            System.out.println("No questions found for the specified topic.");
        }
    }

    public void getRandomQuestion() {
        Question question = questionService.getRandomQuestion();
        if (question != null) {
            System.out.println("Question: " + question.getText());
        } else {
            System.out.println("No questions found.");
        }
    }

    public void addQuestion() {
        System.out.println("Enter the question text: ");
        String questionText = scanner.nextLine();
        System.out.println("Enter the topic name: ");
        String topicName = scanner.nextLine();
        Topic topic = new Topic(0, topicName, "", null);
        Question question = new Question(0, questionText, topic.getId(), null);
        questionService.addQuestion(question);
        System.out.println("Question added successfully.");
    }


    public void deleteQuestion() {
        System.out.println("Enter the question ID: ");
        int questionId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        try {
            questionService.removeQuestion(questionId);
            System.out.println("Question deleted successfully.");
        } catch (RecordsNotDeletedException e) {
            System.out.println("Error occurred while deleting the question: " + e.getMessage());
        }
    }

    public void getTopics() {
        List<Topic> topics = questionService.getTopics();
        System.out.println("Topics:");
        for (Topic topic : topics) {
            System.out.println(topic.getName());
        }
    }

    public void saveTopics() {
        System.out.println("Enter the topic name: ");
        String topicName = scanner.nextLine();
        Topic topic = new Topic(0, topicName, "", null); // Create a Topic object with a temporary ID and empty description
        questionService.saveTopic(topic);
        System.out.println("Topic saved successfully.");
    }
}
