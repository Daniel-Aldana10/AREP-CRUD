package org.edu.crud.service;

import org.edu.crud.model.Property;
import org.edu.crud.repository.PropertyRepository;

import java.util.List;

public class PropertyServiceimpl implements PropertyService{
    private final PropertyRepository propertyRepository;

    public PropertyServiceimpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property create(Property property) {
        validateData(property);

        return propertyRepository.save(property);
    }

    @Override
    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property findById(Long idProperty) {
        return propertyRepository.findById(idProperty).orElse(null);
    }

    @Override
    public Property update(Property property) {
        Property propertyOld = propertyRepository.findById(property.getId()).orElse(null);
        if(propertyOld==null){
            throw new IllegalArgumentException("Property not found with id: " + property.getId());
        }
        propertyOld.setAddress(property.getAddress());
        propertyOld.setSize(property.getSize());
        propertyOld.setPrice(property.getPrice());
        propertyOld.setDescription(property.getDescription());
        return propertyRepository.save(propertyOld);
    }

    @Override
    public void delete(Long idProperty) {
        propertyRepository.deleteById(idProperty);
    }
    private void validateData(Property property){
        if (property.getAddress() == null || property.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Address is required");
        }
        if (property.getPrice() == null || property.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be positive and not null");
        }
        if (property.getSize() == null || property.getSize() <= 0) {
            throw new IllegalArgumentException("Size must be positive and not null");
        }
        if (property.getDescription() == null || property.getDescription().trim().isEmpty() || property.getDescription().length() > 200) {
            throw new IllegalArgumentException("The description is required and must not exceed 200 characters.");
        }
    }
}
