package com.realnest.realnestbackend.controller;

import com.realnest.realnestbackend.model.Property;
import com.realnest.realnestbackend.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PropertyUIController {

    @Autowired
    private PropertyService service;

    // ✅ 1. SHOW ALL PROPERTIES
    @GetMapping("/properties-ui")
    public String showProperties(Model model) {
        model.addAttribute("properties", service.getAll());
        return "properties";
    }

    // ✅ ⭐ NEW: SEARCH
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("properties", service.search(keyword));
        return "properties";
    }

    // ✅ 2. SHOW ADD FORM PAGE
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("property", new Property());
        return "add-property";
    }

    // ✅ 3. SAVE PROPERTY (FORM SUBMIT)
    @PostMapping("/add-property")
    public String addProperty(@ModelAttribute Property property) {
        service.save(property);
        return "redirect:/properties-ui";
    }

    // ✅ 4. DELETE PROPERTY
    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable String id) {
        service.delete(id);
        return "redirect:/properties-ui";
    }

    // ✅ 5. SHOW EDIT PAGE
    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable String id, Model model) {
        Property property = service.getById(id);
        model.addAttribute("property", property);
        return "edit-property";
    }

    // ✅ 6. UPDATE PROPERTY
    @PostMapping("/update/{id}")
    public String updateProperty(@PathVariable String id,
                                 @ModelAttribute Property property) {
        service.updateProperty(id, property);
        return "redirect:/properties-ui";
    }
}