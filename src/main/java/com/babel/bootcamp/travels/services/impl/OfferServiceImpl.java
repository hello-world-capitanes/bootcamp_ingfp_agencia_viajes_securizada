package com.babel.bootcamp.travels.services.impl;

import com.babel.bootcamp.travels.model.Hotel;
import com.babel.bootcamp.travels.model.Trip;
import com.babel.bootcamp.travels.model.TripOffer;
import com.babel.bootcamp.travels.services.HotelService;
import com.babel.bootcamp.travels.services.OfferService;
import com.babel.bootcamp.travels.services.TripService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

	private TripService tripService;
	private HotelService hotelService;

	public OfferServiceImpl(TripService tripService, HotelService hotelService) {
		this.tripService = tripService;
		this.hotelService = hotelService;
	}

	@Override
	public List<TripOffer> getOffersForRandomTrip() {
		Trip trip = tripService.getRandomTrip();
		List<Hotel> hotels = hotelService.getHotelsFromCity(trip.getDestination());
		return hotels.stream().map(hotel -> new TripOffer(trip, hotel)).toList();
	}
}
