package com.realnest.realnestbackend.repository;

import com.realnest.realnestbackend.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String> {

    List<Property> findByTitleContainingIgnoreCase(String keyword);

    List<Property> findByLocationContainingIgnoreCase(String keyword);
}