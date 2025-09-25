package org.edu.crud.controller;


import org.edu.crud.model.Property;
import org.edu.crud.service.PropertyService;
import org.hibernate.PropertyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/properties")
@CrossOrigin("*")
public class PropertyController {


    private final PropertyService service;


    public PropertyController(PropertyService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Property> create(@RequestBody Property property) {
        Property created = service.create(property);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Property>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Property> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    @PutMapping
    public ResponseEntity<Property> update(@RequestBody Property property) {
        return ResponseEntity.ok(service.update(property));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<String> handleNotFound(PropertyNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
