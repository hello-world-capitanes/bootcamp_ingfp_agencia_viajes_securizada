package com.babel.bootcamp.travels.dao;

import com.babel.bootcamp.travels.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripDao {
	void addTrip(String destination, int duration, int basePrice);

	void deleteTrip(String destination);

	void updateTrip(String destination, int duration, int basePrice);

	Optional<Trip> getTrip(String destination);

	Trip getRandomTrip();

	List<Trip> getTrips();
}
