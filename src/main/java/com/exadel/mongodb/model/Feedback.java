package com.exadel.mongodb.model;

import org.springframework.data.annotation.Id;

public class Feedback {

    @Id
    private String id;
    private String authorId;
    private String entityId;
    private String description;
    private Integer score;
    private String sha256Hex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSha256Hex() {
        return sha256Hex;
    }

    public void setSha256Hex(String sha256Hex) {
        this.sha256Hex = sha256Hex;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", description='" + description + '\'' +
                ", score=" + score +
                ", sha256Hex='" + sha256Hex + '\'' +
                '}';
    }
}
