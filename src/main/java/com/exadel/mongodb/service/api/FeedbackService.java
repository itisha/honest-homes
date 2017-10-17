package com.exadel.mongodb.service.api;

import com.exadel.mongodb.model.Feedback;

import java.util.List;

public interface FeedbackService extends Service<Feedback, String> {
    List<Feedback> findByAuthorId(String authorId);

    List<Feedback> findByEntityId(String entityId);
}
