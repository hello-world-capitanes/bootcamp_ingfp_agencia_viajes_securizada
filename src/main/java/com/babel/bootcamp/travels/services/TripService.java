package com.babel.bootcamp.travels.services;

import com.babel.bootcamp.travels.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripService {

	void addTrip(Trip trip);

	void deleteTrip(String destination);

	void updateTrip(String destination, Trip trip);

	Optional<Trip> getTrip(String destination);

	Trip getRandomTrip();

	List<Trip> getTrips();

}
