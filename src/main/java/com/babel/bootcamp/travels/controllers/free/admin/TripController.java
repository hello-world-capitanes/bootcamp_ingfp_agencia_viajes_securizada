package com.babel.bootcamp.travels.controllers.free.admin;

import com.babel.bootcamp.travels.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripController {
	void addTrip(Trip trip);

	void deleteTrip(String destination);

	void updateTrip(String destination, Trip trip);

	Trip getTrip(String destination);

	List<Trip> getTrips();
}
