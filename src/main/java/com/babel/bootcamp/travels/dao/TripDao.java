package com.babel.bootcamp.travels.dao;

import com.babel.bootcamp.travels.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripDao {
	void addTrip(Trip trip);

	void deleteTrip(String destination);

	void updateTrip(Trip trip);

	Optional<Trip> getTrip(String destination);

	Trip getRandomTrip();

	List<Trip> getTrips();
}
