package com.realnest.realnestbackend.service;

import com.realnest.realnestbackend.model.User;
import com.realnest.realnestbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // ✅ REGISTER
    public User save(User user) {
        return repository.save(user);
    }

    // ✅ LOGIN (returns User, NOT boolean)
    public User login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }
}