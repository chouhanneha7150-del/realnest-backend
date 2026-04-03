package com.realnest.realnestbackend.controller;

import com.realnest.realnestbackend.model.Property;
import com.realnest.realnestbackend.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PropertyUIController {

    @Autowired
    private PropertyService service;

    // ✅ SHOW ALL PROPERTIES
    @GetMapping("/properties")
    public String viewProperties(Model model) {
        List<Property> list = service.getAllProperties();
        model.addAttribute("properties", list);
        return "properties";
    }

    // ✅ SHOW ADD FORM
    @GetMapping("/add-property")
    public String showAddForm(Model model) {
        model.addAttribute("property", new Property());
        return "add-property";
    }

    // ✅ SAVE PROPERTY
    @PostMapping("/save-property")
    public String saveProperty(@ModelAttribute Property property) {
        service.createProperty(property);
        return "redirect:/properties";
    }

    // ✅ DELETE PROPERTY
    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable String id) {
        service.deleteProperty(id);
        return "redirect:/properties";
    }

    // ✅ SEARCH
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Property> results = service.searchProperties(keyword);
        model.addAttribute("properties", results);
        return "properties";
    }
}