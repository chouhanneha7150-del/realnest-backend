package com.realnest.realnestbackend.service;

import com.realnest.realnestbackend.model.Property;
import com.realnest.realnestbackend.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repository;

    // ✅ CREATE
    public Property save(Property property) {
        return repository.save(property);
    }

    // ✅ READ ALL
    public List<Property> getAll() {
        return repository.findAll();
    }

    // ✅ GET BY ID
    public Property getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));
    }

    // ✅ SEARCH
    public List<Property> search(String keyword) {
        List<Property> byTitle = repository.findByTitleContainingIgnoreCase(keyword);
        List<Property> byLocation = repository.findByLocationContainingIgnoreCase(keyword);

        byTitle.addAll(byLocation);
        return byTitle;
    }

    // ✅ UPDATE (🔥 FIXED)
    public Property updateProperty(String id, Property newProperty) {

        Property existingProperty = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));

        existingProperty.setTitle(newProperty.getTitle());
        existingProperty.setLocation(newProperty.getLocation());
        existingProperty.setPrice(newProperty.getPrice());

        return repository.save(existingProperty);
    }

    // ✅ DELETE
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Property not found with id: " + id);
        }
        repository.deleteById(id);
    }
}