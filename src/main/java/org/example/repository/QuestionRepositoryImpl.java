package org.example.repository;

import org.example.SingletonConnection;
import org.example.dao.QuestionRepository;
import org.example.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final Connection connection;
    private static final String SAVE_QUERY = "INSERT INTO question (text, topic_id, created_at) VALUES (?, ?, ?)";
    private static final String GET_QUERY = "SELECT * FROM question WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM question WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE question SET text = ?, topic_id = ? WHERE id = ?";


    public QuestionRepositoryImpl() {
        connection = SingletonConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Question question) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            statement.setString(1, question.getText());
            statement.setInt(2, question.getTopicId());
            statement.setInt(3, question.getCreatedAt());
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
                    int createdAt = resultSet.getInt("created_at");
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
}
