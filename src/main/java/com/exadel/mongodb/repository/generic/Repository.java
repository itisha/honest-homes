package com.exadel.mongodb.repository.generic;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface Repository<T, ID extends Serializable> extends MongoRepository<T, ID> {
}
