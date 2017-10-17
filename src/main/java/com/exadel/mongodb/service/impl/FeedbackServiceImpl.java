package com.exadel.mongodb.service.impl;

import com.exadel.ethereum.api.EthereumService;
import com.exadel.mongodb.model.Feedback;
import com.exadel.mongodb.repository.FeedbackRepository;
import com.exadel.mongodb.service.api.AbstractService;
import com.exadel.mongodb.service.api.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl extends AbstractService<Feedback, String> implements FeedbackService {

    private EthereumService ethereumService;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository repository, EthereumService ethereumService) {
        super(repository);
        this.ethereumService = ethereumService;
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
        ethereumService.storeFeedbackSha256Hex(feedback);
        return super.save(feedback);
    }

    private FeedbackRepository getRepositoryInstance() {
        return (FeedbackRepository) repository;
    }
}
