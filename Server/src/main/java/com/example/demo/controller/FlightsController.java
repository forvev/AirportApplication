package com.example.demo.controller;

import com.example.demo.model.Flights;
import com.example.demo.model.Seat;
import com.example.demo.repository.ItemRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@ComponentScan
@RestController
// you specify the origins that are allowed to access the resources (security reasons)
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class FlightsController {

    //This annotation tells Spring to inject an instance of the ItemRepository
    // into the objectRepository field. By using @Autowired, you don't need to explicitly
    // create an instance of ItemRepository or manually wire the dependency. Spring will handle
    // the dependency injection for you.
    @Autowired
    private final ItemRepository objectRepository;

    public FlightsController(ItemRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    @GetMapping("/flights")
    public List<Flights> getAllObjects() {
        return objectRepository.findAll();
    }

    //check if the seat is reserved by row and column
    @GetMapping("/{id}/{rowNo}/{columnNo}")
    public boolean checkIfReserved(@PathVariable ObjectId id, @PathVariable char rowNo, @PathVariable int columnNo){
        Seat mySeat = objectRepository.is_seat_reserved_in_plane(id, rowNo, columnNo);

        return mySeat.isReserved();
    }

    @GetMapping("/test")
    public String test(){ return "it works";}


}