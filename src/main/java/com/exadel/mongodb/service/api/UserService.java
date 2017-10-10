package com.exadel.mongodb.service.api;

import com.exadel.mongodb.model.User;

import java.util.List;

public interface UserService extends Service<User, String> {
    List<User> findByLastName(String lastName);
}
