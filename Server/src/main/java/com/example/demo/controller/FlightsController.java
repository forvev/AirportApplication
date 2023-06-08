package com.example.demo.controller;

import com.example.demo.model.Flights;
import com.example.demo.model.Seat;
import com.example.demo.repository.ItemRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @GetMapping("/flights/{id}")
    public Optional<Flights> findById(@PathVariable String id){
        System.out.println("id: "+id);
        return objectRepository.findById(id);
    }

    //check if the seat is reserved by row and column
    @GetMapping("/flights/{id}/{rowNo}/{columnNo}")
    public boolean checkIfReserved(@PathVariable String id, @PathVariable String rowNo, @PathVariable String columnNo){
        Flights myFlight = objectRepository.is_seat_reserved_in_plane(id, rowNo, columnNo);

        Set<Seat> mySeats = myFlight.getFlight_seats();

        for(Seat seat: mySeats){
            System.out.println(seat.toString());
            if(seat.getRow() == rowNo.charAt(0) && seat.getColumn() == Integer.parseInt(columnNo)) {
                return seat.isReserved();
            }
        }
        return false;
    }

    @GetMapping("/test")
    public String test(){ return "it works";}


}