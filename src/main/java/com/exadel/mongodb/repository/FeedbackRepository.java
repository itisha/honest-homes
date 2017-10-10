package com.exadel.mongodb.repository;

import com.exadel.mongodb.model.Feedback;
import com.exadel.mongodb.repository.generic.Repository;

import java.util.List;

public interface FeedbackRepository extends Repository<Feedback, String> {
    List<Feedback> findByAuthorId(String landlordId);
    List<Feedback> findByEntityId(String landlordId);
}
