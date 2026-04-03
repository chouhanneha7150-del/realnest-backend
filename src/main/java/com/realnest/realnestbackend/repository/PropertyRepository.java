package com.realnest.realnestbackend.repository;

import com.realnest.realnestbackend.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyRepository extends MongoRepository<Property, String> {
}