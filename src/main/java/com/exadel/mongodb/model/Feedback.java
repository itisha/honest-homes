package com.exadel.mongodb.model;

import org.springframework.data.annotation.Id;

public class Feedback {

    @Id
    private String id;
    private String authorId;
    private String entityId;
    private String description;
    private Integer score;
    private String hashcode;

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

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", description='" + description + '\'' +
                ", score=" + score +
                ", hashcode='" + hashcode + '\'' +
                '}';
    }
}
