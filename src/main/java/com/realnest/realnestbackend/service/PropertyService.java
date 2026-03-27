package com.realnest.realnestbackend.service;

import com.realnest.realnestbackend.model.Property;
import com.realnest.realnestbackend.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repository;

    // ✅ CREATE
    public Property save(Property property) {
        return repository.save(property);
    }

    // ✅ READ (GET ALL)
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

        // Remove duplicates (important)
        Set<Property> result = new HashSet<>();
        result.addAll(byTitle);
        result.addAll(byLocation);

        return new ArrayList<>(result);
    }

    // ✅ UPDATE (FIXED & SAFE)
    public Property updateProperty(String id, Property newProperty) {

        Property existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));

        // Update only if values are provided
        if (newProperty.getTitle() != null) {
            existing.setTitle(newProperty.getTitle());
        }

        if (newProperty.getLocation() != null) {
            existing.setLocation(newProperty.getLocation());
        }

        if (newProperty.getPrice() != 0) {
            existing.setPrice(newProperty.getPrice());
        }

        return repository.save(existing);
    }

    // ✅ DELETE
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Property not found with id: " + id);
        }
        repository.deleteById(id);
    }
}