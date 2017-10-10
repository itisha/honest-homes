package com.exadel.mongodb.service.impl;

import com.exadel.mongodb.model.Property;
import com.exadel.mongodb.repository.PropertyRepository;
import com.exadel.mongodb.service.api.AbstractService;
import com.exadel.mongodb.service.api.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl extends AbstractService<Property, String> implements PropertyService {

    @Autowired
    public PropertyServiceImpl(PropertyRepository repository) {
        super(repository);
    }

    @Override
    public List<Property> findByLandlordId(String landlordId) {
        return getRepositoryInstance().findByLandlordId(landlordId);
    }

    private PropertyRepository getRepositoryInstance() {
        return (PropertyRepository) repository;
    }
}
