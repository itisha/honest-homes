package com.exadel.mongodb.service.impl;

import com.exadel.mongodb.model.Feedback;
import com.exadel.mongodb.repository.FeedbackRepository;
import com.exadel.mongodb.service.api.AbstractService;
import com.exadel.mongodb.service.api.FeedbackService;
import com.exadel.util.FeedbackUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl extends AbstractService<Feedback, String> implements FeedbackService {

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository repository) {
        super(repository);
    }

    @Override
    public List<Feedback> findByAuthorId(String authorId) {
        return getRepositoryInstance().findByAuthorId(authorId);
    }

    @Override
    public List<Feedback> findByEntityId(String entityId) {
        return getRepositoryInstance().findByEntityId(entityId);
    }

    @Override
    public Feedback save(Feedback feedback) {
        feedback = super.save(feedback);
        String sha256Hex = FeedbackUtils.sha256Hex(feedback);
        feedback.setSha256Hex(sha256Hex);
        //todo store hash in Ethereum
        return super.save(feedback);
    }

    @Override
    public List<Feedback> findBySha256Hex(String sha256Hex) {
        return getRepositoryInstance().findBySha256Hex(sha256Hex);
    }

    private FeedbackRepository getRepositoryInstance() {
        return (FeedbackRepository) repository;
    }
}
