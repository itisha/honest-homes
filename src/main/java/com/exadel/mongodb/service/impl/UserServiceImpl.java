package com.exadel.mongodb.service.impl;

import com.exadel.mongodb.model.User;
import com.exadel.mongodb.repository.UserRepository;
import com.exadel.mongodb.service.api.AbstractService;
import com.exadel.mongodb.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractService<User, String> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return getRepositoryInstance().findByLastName(lastName);
    }

    private UserRepository getRepositoryInstance() {
        return (UserRepository) repository;
    }

}
