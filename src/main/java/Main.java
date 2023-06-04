import org.example.dao.QuestionRepository;
import org.example.dao.TopicRepository;
import org.example.repository.QuestionRepositoryImpl;
import org.example.repository.TopicRepositoryImpl;
import org.example.service.QuestionService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi, friend!");

        Scanner scanner = new Scanner(System.in);
        Map<Command, Runnable> commands = init(scanner);

        while (true) {
            Command command = getCommand(scanner);

            if (command == null) {
                break; // Stop executing commands if an empty command is entered
            }

            Runnable runnable = commands.getOrDefault(command, () -> System.out.println("Incorrect command!"));
            runnable.run();
        }

        System.out.println("There is nothing left. Goodbye friend!");
    }

    private static Map<Command, Runnable> init(Scanner scanner) {
        QuestionRepository questionRepository = new QuestionRepositoryImpl();
        TopicRepository topicRepository = new TopicRepositoryImpl();
        QuestionService questionService = new QuestionService(topicRepository, questionRepository);

        Shell shell = new Shell(questionService, scanner);

        Map<Command, Runnable> commands = new HashMap<>();
        commands.put(Command.GET_QUESTION_BY_TOPIC, shell::getRandomQuestionByTopic);
        commands.put(Command.GET_RANDOM_QUESTION, shell::getRandomQuestion);
        commands.put(Command.ADD_QUESTION, shell::addQuestion);
        commands.put(Command.DELETE_QUESTION, shell::deleteQuestion);
        commands.put(Command.GET_TOPICS, shell::getTopics);
        commands.put(Command.SAVE_TOPICS, shell::saveTopics);

        return commands;
    }

    private static Command getCommand(Scanner scanner) {
        System.out.println("Enter the command (" + getAvailableCommands() + "):");
        String commandName = scanner.nextLine().toUpperCase();

        if (commandName.isEmpty()) {
            return null;
        }

        try {
            return Command.valueOf(commandName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static String getAvailableCommands() {
        StringBuilder sb = new StringBuilder();
        for (Command command : Command.values()) {
            sb.append(command.name()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}

