package com.exadel.mongodb;

import com.exadel.mongodb.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String> {
    List<Property> findByLandlordId(String landlordId);
}
