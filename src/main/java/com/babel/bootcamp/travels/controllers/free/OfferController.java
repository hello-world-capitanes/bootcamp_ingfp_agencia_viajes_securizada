package com.babel.bootcamp.travels.controllers.free;

import com.babel.bootcamp.travels.model.TripOffer;

import java.util.List;

public interface OfferController {
	List<TripOffer> getOffersForRandomTrip();
}
