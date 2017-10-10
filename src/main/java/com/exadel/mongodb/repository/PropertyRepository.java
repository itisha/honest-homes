package com.exadel.mongodb.repository;

import com.exadel.mongodb.model.Property;
import com.exadel.mongodb.repository.generic.Repository;

import java.util.List;

public interface PropertyRepository extends Repository<Property, String> {
    List<Property> findByLandlordId(String landlordId);
}
