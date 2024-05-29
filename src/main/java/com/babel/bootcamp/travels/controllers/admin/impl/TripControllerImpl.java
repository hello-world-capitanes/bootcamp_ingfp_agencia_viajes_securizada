package com.babel.bootcamp.travels.controllers.admin.impl;

import com.babel.bootcamp.travels.controllers.admin.TripController;
import com.babel.bootcamp.travels.model.Trip;
import com.babel.bootcamp.travels.services.NotFoundException;
import com.babel.bootcamp.travels.services.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travel-agency/admin/trips")
public class TripControllerImpl implements TripController {
	private TripService tripService;

	public TripControllerImpl(TripService tripService) {
		this.tripService = tripService;
	}

	@Override
	@PostMapping("")
	public void addTrip(@RequestBody Trip trip) {
		tripService.addTrip(trip);
	}

	@Override
	@DeleteMapping("/{destination}")
	public void deleteTrip(@PathVariable String destination) {
		tripService.deleteTrip(destination);
	}

	@Override
	@PutMapping("/{destination}")
	public void updateTrip(@PathVariable String destination, @RequestBody Trip trip) {
		try {
			tripService.updateTrip(destination, trip);
		}catch(NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found");
		}
	}

	@Override
	@GetMapping("/{destination}")
	public Trip getTrip(@PathVariable String destination) {
		Optional<Trip> trip = tripService.getTrip(destination);
		return trip.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));
	}

	@Override
	@GetMapping("")
	public List<Trip> getTrips() {
		return tripService.getTrips();
	}

}
