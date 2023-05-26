package org.example.model;

public class Question {
    private int id;
    private String text;
    private int topicId;
    private int createdAt;

    public Question(int id, String text, int topic_id, int createdAt) {
        this.id = id;
        this.text = text;
        this.topicId = topic_id;
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

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }
}
