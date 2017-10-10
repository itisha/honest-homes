package com.exadel.mongodb.service.api;

import java.io.Serializable;
import java.util.List;

public interface Service<T, ID extends Serializable> {
    List<T> findAll();

    T findOne(ID id);

    T save(T entiity);

    boolean exists(ID id);

    boolean delete(ID id);
}
