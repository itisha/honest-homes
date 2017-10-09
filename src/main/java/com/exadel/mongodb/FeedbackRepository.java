package com.exadel.mongodb;

import com.exadel.mongodb.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    List<Feedback> findByAuthorId(String landlordId);

    List<Feedback> findByEntityId(String landlordId);
}
