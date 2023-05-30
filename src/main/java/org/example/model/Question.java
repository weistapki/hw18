package org.example.model;

import java.sql.Timestamp;

public class Question {
    private int id;
    private String text;
    private int topicId;
    private Timestamp createdAt;

    public Question(int id, String text, int topicId, Timestamp createdAt) {
        this.id = id;
        this.text = text;
        this.topicId = topicId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", topicId=" + topicId +
                ", createdAt=" + createdAt +
                '}';
    }
}

