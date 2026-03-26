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

    // ✅ CREATE
    @PostMapping
    public Property addProperty(@RequestBody Property property) {
        return service.save(property);
    }

    // ✅ READ (GET ALL)
    @GetMapping
    public List<Property> getAllProperties() {
        return service.getAll();
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable String id, @RequestBody Property property) {
        return service.updateProperty(id, property);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String deleteProperty(@PathVariable String id) {
        service.delete(id);
        return "Property deleted successfully!";
    }
}