package com.exadel;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LandlordRepository extends MongoRepository<Landlord, String> {

    public Landlord findByFirstName(String firstName);

    public List<Landlord> findByLastName(String lastName);

}
