package org.example.repository;

import org.example.SingletonConnection;
import org.example.dao.QuestionRepository;
import org.example.exaption.IdNotFoundException;
import org.example.model.Question;
import org.example.model.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final Connection connection;
    private static final String SAVE_QUERY = "INSERT INTO question (text, topic_id, created_at) VALUES (?, ?, ?)";
    private static final String GET_QUERY = "SELECT * FROM question WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM question WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE question SET text = ?, topic_id = ? WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM question";


    public QuestionRepositoryImpl() {
        connection = SingletonConnection.getInstance().getConnection();
    }

    @Override
    public Question save(Question question) throws IdNotFoundException {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, question.getText());
            statement.setInt(2, question.getTopicId());
            statement.setTimestamp(3, question.getCreatedAt());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                question.setId(generatedKeys.getInt(1));
                return question;
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while saving question: " + e.getMessage());
        }
        throw new IdNotFoundException();
    }


    @Override
    public Question get(int id) throws IdNotFoundException {
        try (PreparedStatement statement = connection.prepareStatement(GET_QUERY)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String text = resultSet.getString("text");
                    int topicId = resultSet.getInt("topic_id");
                    Timestamp createdAt = resultSet.getTimestamp("created_at");
                    return new Question(id, text, topicId, createdAt);
                }
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while retrieving the question: " + e.getMessage());
            throw new IdNotFoundException();
        }
    }

    @Override
    public boolean remove(int id) throws IdNotFoundException {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_QUERY)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error occurred while removing the question with ID: " + id + ": " + e.getMessage());
            throw new IdNotFoundException();
        }
    }
    public boolean update(Question question) throws IdNotFoundException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, question.getText());
            statement.setInt(2, question.getTopicId());
            statement.setInt(3, question.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error occurred while updating the question: " + e.getMessage());
            throw new IdNotFoundException();
        }
    }
    @Override
    public List<Question> getAllByTopic() throws IdNotFoundException {
        List<Question> questions = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String text = resultSet.getString("text");
                    int topicId = resultSet.getInt("topic_id");
                    Timestamp createdAt = resultSet.getTimestamp("created_at");
                    questions.add(new Question(id, text, topicId, createdAt));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while retrieving the list of questions: " + e.getMessage());
            throw new IdNotFoundException();
        }
        return questions;
    }
}
