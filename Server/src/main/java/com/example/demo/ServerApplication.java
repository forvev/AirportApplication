package com.example.demo;

import com.example.demo.model.Flights;
import com.example.demo.model.Seat;
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

		//createFlights();
		createSeats(1);
	}

	private void createFlights(){
		System.out.println("I have just created some flights!");

		flightItemRepo.save(new Flights("1","test", "223","AC", "2234", "Warsaw", "Frankfurt", "2"));
		System.out.println("Done");
		//flightItemRepo.findAll()
	}

	//Seats of the specific plane are created
	private void createSeats(int planeType){
		//1 - boeing 777
		Set<Seat> mySeats = new HashSet<>();

		//TODO: add emergency seats
		if (planeType == 1){
			for(int row=65; row<73; row++){
				for(int column=1; column<54; column++){
					//first_class
					if ( (row >= 65 && row <= 72) && (column >= 1 && column <= 15)){
						Seat tmp_seat = new Seat(Seat.SeatClass.FIRST, false, column, (char)row);
						mySeats.add(tmp_seat);
					}
					//economy plus
					else if ((row >= 65 && row <= 72) && (column >= 16 && column <= 33)){
						Seat tmp_seat = new Seat(Seat.SeatClass.ECONOMY_PLUS, false, column, (char)row);
						mySeats.add(tmp_seat);
					}
					//economy
					else if ((row >= 65 && row <= 72) && (column >= 34 && column <= 53)){
						Seat tmp_seat = new Seat(Seat.SeatClass.ECONOMY, false, column, (char)row);
						mySeats.add(tmp_seat);
					}
				}

			}
		}

//		for (Seat mseat: mySeats) {
//			System.out.print(mseat.toString());
//		}
		
	}
}



