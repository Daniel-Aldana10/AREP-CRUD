package org.edu.crud.service;

import org.edu.crud.model.Property;

import java.util.List;

public interface PropertyService {
    Property create(Property property);
    List<Property> findAll();
    Property findById(Long idProperty);
    Property update(Property property);
    void delete(Long idProperty);
}

