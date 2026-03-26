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

    // ✅ SEARCH (ADD HERE 👇)
    public List<Property> search(String keyword) {
        List<Property> byTitle = repository.findByTitleContainingIgnoreCase(keyword);
        List<Property> byLocation = repository.findByLocationContainingIgnoreCase(keyword);

        byTitle.addAll(byLocation);
        return byTitle;
    }

    // ✅ UPDATE
    public Property updateProperty(String id, Property newProperty) {
        Optional<Property> existing = repository.findById(id);

        if (existing.isPresent()) {
            Property property = existing.get();

            property.setTitle(newProperty.getTitle());
            property.setLocation(newProperty.getLocation());
            property.setPrice(newProperty.getPrice());

            return repository.save(property);
        } else {
            throw new RuntimeException("Property not found with id: " + id);
        }
    }

    // ✅ DELETE
    public void delete(String id) {
        repository.deleteById(id);
    }
}