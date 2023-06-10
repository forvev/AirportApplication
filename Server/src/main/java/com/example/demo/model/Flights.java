package com.example.demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("flights")
public class Flights {
    @Id
    private String id;
    private String operatingAirline, iataCode, aircraftModelNameComboBox,flightNumber, departureAirport, arrivalAirport, departureTerminal;

    private Set<Seat> flight_seats;
    public Flights(String id, String operatingAirline, String iataCode, String aircraftModelNameComboBox, String flightNumber, String departureAirport, String arrivalAirport, String departureTerminal, Set<Seat> flight_seats) {
        super();
        this.id = id;
        this.operatingAirline = operatingAirline;
        this.iataCode = iataCode;
        this.aircraftModelNameComboBox = aircraftModelNameComboBox;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTerminal = departureTerminal;
        this.flight_seats = flight_seats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOperatingAirline(String operatingAirline) {
        this.operatingAirline = operatingAirline;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public void setAircraftModelNameComboBox(String aircraftModelNameComboBox) {
        this.aircraftModelNameComboBox = aircraftModelNameComboBox;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public String getOperatingAirline() {
        return operatingAirline;
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getAircraftModelNameComboBox() {
        return aircraftModelNameComboBox;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    public Set<Seat> getFlight_seats() {
        return flight_seats;
    }

    public void setFlight_seats(Set<Seat> flight_seats) {
        this.flight_seats = flight_seats;
    }
}
