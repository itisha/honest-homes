package com.exadel.mongodb.repository;

import com.exadel.mongodb.model.Feedback;
import com.exadel.mongodb.repository.generic.Repository;

import java.util.List;

public interface FeedbackRepository extends Repository<Feedback, String> {
    List<Feedback> findByAuthorId(String authorId);

    List<Feedback> findByEntityId(String entityId);

    List<Feedback> findBySha256Hex(String sha256Hex);
}
