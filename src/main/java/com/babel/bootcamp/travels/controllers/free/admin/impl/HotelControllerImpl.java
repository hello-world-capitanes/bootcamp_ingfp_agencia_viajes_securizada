package com.babel.bootcamp.travels.controllers.free.admin.impl;

import com.babel.bootcamp.travels.controllers.free.admin.HotelController;
import com.babel.bootcamp.travels.model.Hotel;
import com.babel.bootcamp.travels.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travel-agency/admin/hotels")
public class HotelControllerImpl implements HotelController {

	private HotelService hotelService;


	public HotelControllerImpl(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	@Override
	@PostMapping("")
	public void addHotel(@RequestBody Hotel hotel) {
		hotelService.addHotel(hotel);
	}

	@Override
	@DeleteMapping("/{city}/{name}")
	public void deleteHotel(@PathVariable String name, @PathVariable String city) {
		hotelService.deleteHotel(name, city);
	}

	@Override
	@PutMapping("/{city}/{name}")
	public void updateHotel(@PathVariable String name, @PathVariable String city, @RequestBody Hotel hotel) {

		try {
			hotelService.updateHotel(name, city, hotel);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
		}

	}

	@Override
	@GetMapping("/{city}/{name}")
	public Hotel getHotel(@PathVariable String name, @PathVariable String city) {
		Optional<Hotel> hotel = hotelService.getHotel(name, city);
		return hotel.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found"));
	}

	@Override
	@GetMapping("")
	public List<Hotel> getHotels() {
		return hotelService.getHotels();
	}
}
