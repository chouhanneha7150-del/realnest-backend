package com.realnest.realnestbackend.repository;

import com.realnest.realnestbackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmailAndPassword(String email, String password);
}