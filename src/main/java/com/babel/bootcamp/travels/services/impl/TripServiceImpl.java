package com.babel.bootcamp.travels.services.impl;

import com.babel.bootcamp.travels.dao.TripDao;
import com.babel.bootcamp.travels.model.Trip;
import com.babel.bootcamp.travels.services.NotFoundException;
import com.babel.bootcamp.travels.services.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {

	private TripDao tripDao;

	public TripServiceImpl(TripDao tripDao) {
		this.tripDao = tripDao;
	}

	@Override
	public void addTrip(Trip trip) {
		tripDao.addTrip(trip);
	}

	@Override
	public void deleteTrip(String destination) {
		tripDao.deleteTrip(destination);
	}

	@Override
	public void updateTrip(String destination, Trip trip) {

		Optional<Trip> storedTrip = getTrip(destination);
		if (storedTrip.isEmpty()) {
			throw new NotFoundException("Trip not found");
		}
		trip.setDestination(destination);

		tripDao.updateTrip(trip);
	}

	@Override
	public Optional<Trip> getTrip(String destination) {
		return tripDao.getTrip(destination);
	}

	@Override
	public Trip getRandomTrip() {
		return tripDao.getRandomTrip();
	}

	@Override
	public List<Trip> getTrips() {
		return tripDao.getTrips();
	}
}
