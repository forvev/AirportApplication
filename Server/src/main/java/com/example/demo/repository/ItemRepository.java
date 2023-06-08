package com.example.demo.repository;

import com.example.demo.model.Flights;
import com.example.demo.model.Seat;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;

import java.util.List;

//This interface is responsible for CRUD operations
@Repository
@EnableMongoRepositories
public interface ItemRepository extends MongoRepository<Flights, String> {

    @Query("{name: '?0'}")
    Flights findItemByName(String name);

    //We only want to project the field's name and quantity in the query response, so we set those fields to 1.
    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<Flights> findAll(String category);

    @Query("{_id: '?0'}")//, 'flight_seats.column': ?2}")
    Flights is_seat_reserved_in_plane(String id, String rowNo, String columnNo);


    public long count();
}
