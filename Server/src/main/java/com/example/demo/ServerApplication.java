package com.example.demo;

import com.example.demo.model.Flights;
import com.example.demo.model.Seat;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.example.demo.repository.ItemRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

@SpringBootApplication//(scanBasePackages = {"com.example.repository", "com.example.model"})
@EnableMongoRepositories("com.example.demo.repository")
public class ServerApplication implements CommandLineRunner {

	@Autowired
	ItemRepository flightItemRepo;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//createFlights();
	}

	private void createFlights(){
		System.out.println("I have just created some flights!");

		ObjectId myid = new ObjectId();

		Set <Seat> seats = createSeats(1);
		flightItemRepo.save(new Flights(myid,"LOT", "223",
				"777", "2234", "Warsaw",
				"Frankfurt", "2", seats));

		myid = new ObjectId();
		flightItemRepo.save(new Flights(myid,"Lufthansa", "542",
				"777", "2314", "New York",
				"Madrid", "1", seats));

		myid = new ObjectId();
		flightItemRepo.save(new Flights(myid,"Qatar Airlines", "111",
				"777", "3467", "Barcelona",
				"Paris", "11", seats));

		myid = new ObjectId();
		flightItemRepo.save(new Flights(myid,"Fly Emirates", "231",
				"777", "1234", "Moscow",
				"Berlin", "6", seats));

		System.out.println("Done");
	}

	//Seats of the specific plane are created
	private Set<Seat> createSeats(int planeType){
		//1 - boeing 777
		Set<Seat> mySeats = new HashSet<>();

		//TODO: add emergency seats
		if (planeType == 1){
			for(int row=65; row<73; row++){
				for(int column=1; column<54; column++){
					//first_class
					if ( (row >= 65 && row <= 72) && (column >= 1 && column <= 15)){
						Seat tmp_seat = new Seat(Seat.SeatClass.FIRST, false, column, (char)row, false);
						mySeats.add(tmp_seat);
					}
					//economy plus
					else if ((row >= 65 && row <= 72) && (column >= 16 && column <= 33)){
						Seat tmp_seat = new Seat(Seat.SeatClass.ECONOMY_PLUS, false, column, (char)row, false);
						mySeats.add(tmp_seat);
					}
					//economy
					else if ((row >= 65 && row <= 72) && (column >= 34 && column <= 53)){
						Seat tmp_seat = new Seat(Seat.SeatClass.ECONOMY, false, column, (char)row, false);
						mySeats.add(tmp_seat);
					}
				}

			}
		}

//		for (Seat mseat: mySeats) {
//			System.out.print(mseat.toString());
//		}
		return mySeats;
	}
}



