package com.exadel.mongodb.service.impl;

import com.exadel.mongodb.model.Property;
import com.exadel.mongodb.model.User;
import com.exadel.mongodb.repository.PropertyRepository;
import com.exadel.mongodb.service.api.AbstractService;
import com.exadel.mongodb.service.api.PropertyService;
import com.exadel.mongodb.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl extends AbstractService<Property, String> implements PropertyService {

    private UserService userService;

    @Autowired
    public PropertyServiceImpl(PropertyRepository repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }

    @Override
    public List<Property> findByLandlordId(String landlordId) {
        return getRepositoryInstance().findByLandlordId(landlordId);
    }

    @Override
    public Property save(Property property) {
        if (property.getLandlordId() == null) {
            throw new RuntimeException("Cannot add property without owner. " +
                    "Landlord id must be provided.");
        }

        User owner = userService.findOne(property.getLandlordId());
        if (owner == null) {
            throw new RuntimeException("Owner not found.");
        }

        Property saved = super.save(property);
        owner.addOwnedPropertyId(saved.getId());
        userService.save(owner);
        return saved;
    }

    private PropertyRepository getRepositoryInstance() {
        return (PropertyRepository) repository;
    }
}
