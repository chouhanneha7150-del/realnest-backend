package com.realnest.realnestbackend.controller;

import com.realnest.realnestbackend.model.Property;
import com.realnest.realnestbackend.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService service;

    // ✅ TEST API (ADD THIS)
    @GetMapping("/test")
    public String test() {
        return "Working!";
    }

    // ✅ CREATE
    @PostMapping
    public Property create(@RequestBody Property property) {
        return service.createProperty(property);
    }

    // ✅ GET ALL
    @GetMapping
    public List<Property> getAll() {
        return service.getAllProperties();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public Property getById(@PathVariable String id) {
        return service.getPropertyById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Property update(@PathVariable String id, @RequestBody Property property) {
        return service.updateProperty(id, property);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteProperty(id);
    }
}