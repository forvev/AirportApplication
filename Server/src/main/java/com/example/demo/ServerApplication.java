package com.example.demo;

import com.example.demo.model.Flights;
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

import java.util.List;
import java.util.Optional;
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
	}

	private void createFlights(){
		System.out.println("I have just created some flights!");

		flightItemRepo.save(new Flights("1","test", "223","AC", "2234", "Warsaw", "Frankfurt", "2"));
		System.out.println("Done");
		//flightItemRepo.findAll()
	}
}



