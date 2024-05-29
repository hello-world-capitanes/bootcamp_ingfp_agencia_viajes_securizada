package com.babel.bootcamp.travels.controllers.free.impl;

import com.babel.bootcamp.travels.controllers.free.OfferController;
import com.babel.bootcamp.travels.model.TripOffer;
import com.babel.bootcamp.travels.services.OfferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travel-agency/public/offers")
public class OfferControllerImpl implements OfferController {

	private OfferService offerService;

	public OfferControllerImpl(OfferService offerService) {
		this.offerService = offerService;
	}

	@GetMapping("/random-trip")
	@Override
	public List<TripOffer> getOffersForRandomTrip() {
		return offerService.getOffersForRandomTrip();
	}
}
