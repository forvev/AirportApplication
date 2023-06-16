package com.example.demo.controller;

import com.example.demo.model.Flights;
import com.example.demo.model.Seat;
import com.example.demo.repository.ItemRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
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
    // create an instance of ItmRepository or manually wire the dependency. Spring will handle
    // the dependency injection for you.
    @Autowired
    private final ItemRepository objectRepository;

    public FlightsController(ItemRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    //todo: if there are no seats left don't return the flight or send a message!
    @GetMapping("/flights")
    public List<Flights> getAllObjects() {
        List<Flights> tempList = objectRepository.findAll();

        List<Flights> myList;

        for(Flights flight: tempList){
            System.out.println("id:"+flight.getId());
        }

        return tempList;
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flights> findById(@PathVariable String id) {
        Optional<Flights> tempOpt = objectRepository.findById(id);
        if (tempOpt.isPresent()) {
            Flights flight = tempOpt.get();
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //check if the seat is reserved by row and column
    @GetMapping("/flights/{id}/{rowNo}/{columnNo}")
    public boolean checkIfReserved(@PathVariable String id, @PathVariable String rowNo, @PathVariable String columnNo){
        Flights myFlight = objectRepository.is_seat_reserved_in_plane(id, rowNo, columnNo);

        Set<Seat> mySeats = myFlight.getFlight_seats();

        for(Seat seat: mySeats){
            if(seat.getRow() == rowNo.charAt(0) && seat.getColumn() == Integer.parseInt(columnNo)) {
                return seat.isReserved();
            }
        }
        return false;
    }

    @PutMapping("/flights/changeStatus/{id}/{row}/{column}")
    public ResponseEntity<String> changeReservationStatus( @PathVariable String id, @PathVariable String row, @PathVariable String column){
        System.out.println("I am in the change status!");
        Optional<Flights> tempList = objectRepository.findById(id);

        Flights myFlight = tempList.get();

        Set <Seat> mySeats = myFlight.getFlight_seats();

        for (Seat seat: mySeats) {
            if (seat.getRow() == row.charAt(0) && seat.getColumn() == Integer.parseInt(column)) {
                seat.setReserved(true);
            }
        }
        return ResponseEntity.ok("Seat status changed successfully");
    }

    @GetMapping("/test")
    public String test(){ return "it works";}


    private class ChangeStatusRequest {
        private String row;
        private String column;
        private String id;

        public String getRow() {
            return row;
        }

        public String getColumn() {
            return column;
        }

        public String getId() {
            return id;
        }
    }
}

