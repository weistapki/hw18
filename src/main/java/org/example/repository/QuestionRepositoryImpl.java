package org.example.repository;

import org.example.SingletonConnection;
import org.example.dao.QuestionRepository;
import org.example.model.Question;

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
    public boolean save(Question question) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            statement.setString(1, question.getText());
            statement.setInt(2, question.getTopicId());
            statement.setTimestamp(3, question.getCreatedAt());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving question", e);
        }
    }

    @Override
    public Question get(int id) {
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
            throw new RuntimeException("Error getting question", e);
        }
    }

    @Override
    public boolean remove(int id) {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_QUERY)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error removing question", e);
        }
    }

    @Override
    public boolean update(Question question) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, question.getText());
            statement.setInt(2, question.getTopicId());
            statement.setInt(3, question.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating question", e);
        }
    }
    @Override
    public List<Question> getAllByTopic() {
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
            throw new RuntimeException("Error getting questions", e);
        }
        return questions;
    }
}
