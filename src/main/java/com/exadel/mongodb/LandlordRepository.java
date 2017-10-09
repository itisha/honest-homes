package com.exadel.mongodb;

import com.exadel.mongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LandlordRepository extends MongoRepository<User, String> {
    List<User> findByLastName(String lastName);
}
