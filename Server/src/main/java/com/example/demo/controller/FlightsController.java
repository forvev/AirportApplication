package com.example.demo.controller;

import com.example.demo.model.Flights;
import com.example.demo.repository.ItemRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@ComponentScan
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")

public class FlightsController {

    private final ItemRepository objectRepository;

    public FlightsController(ItemRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    @GetMapping("/flights")
    public List<Flights> getAllObjects() {
        return objectRepository.findAll();
    }

//    @GetMapping("/tickets")
//    public

}