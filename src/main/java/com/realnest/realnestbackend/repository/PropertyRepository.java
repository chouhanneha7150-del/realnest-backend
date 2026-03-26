package com.realnest.realnestbackend.repository;

import com.realnest.realnestbackend.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String> {

    // ✅ SEARCH METHODS
    List<Property> findByTitleContainingIgnoreCase(String title);

    List<Property> findByLocationContainingIgnoreCase(String location);
}