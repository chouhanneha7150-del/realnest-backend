package com.realnest.realnestbackend.service;

import com.realnest.realnestbackend.model.Property;
import com.realnest.realnestbackend.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repository;

    // ✅ CREATE
    public Property createProperty(Property property) {
        return repository.save(property);
    }

    // ✅ GET ALL
    public List<Property> getAllProperties() {
        return repository.findAll();
    }

    // ✅ GET BY ID
    public Property getPropertyById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));
    }

    // ✅ UPDATE
    public Property updateProperty(String id, Property property) {
        Property existing = getPropertyById(id);

        existing.setTitle(property.getTitle());
        existing.setLocation(property.getLocation());
        existing.setPrice(property.getPrice());

        return repository.save(existing);
    }

    // ✅ DELETE
    public void deleteProperty(String id) {
        repository.deleteById(id);
    }

    // ✅ SEARCH (Optional but powerful 💡)
    public List<Property> searchProperties(String keyword) {
        return repository.findAll()
                .stream()
                .filter(p ->
                        p.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                                p.getLocation().toLowerCase().contains(keyword.toLowerCase())
                )
                .toList();
    }
}