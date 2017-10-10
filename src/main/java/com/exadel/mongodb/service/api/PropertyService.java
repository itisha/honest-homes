package com.exadel.mongodb.service.api;

import com.exadel.mongodb.model.Property;

import java.util.List;

public interface PropertyService extends Service<Property, String> {
    List<Property> findByLandlordId(String landlordId);
}
