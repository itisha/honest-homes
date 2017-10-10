package com.exadel.mongodb.repository;

import com.exadel.mongodb.model.User;
import com.exadel.mongodb.repository.generic.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, String> {
    List<User> findByLastName(String lastName);
}
