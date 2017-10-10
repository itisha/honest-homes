package com.exadel.mongodb.service.api;

import com.exadel.mongodb.repository.generic.Repository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T, ID extends Serializable> implements Service<T, ID> {

    protected Repository<T, ID> repository;

    public AbstractService(Repository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public boolean exists(ID id) {
        return repository.exists(id);
    }

    @Override
    public boolean delete(ID id) {
        if (repository.exists(id)) {
            repository.delete(id);
            return true;
        }
        return false;
    }
}
