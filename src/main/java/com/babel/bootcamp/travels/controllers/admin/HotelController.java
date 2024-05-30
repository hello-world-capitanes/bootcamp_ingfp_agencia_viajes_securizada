package com.babel.bootcamp.travels.controllers.admin;

import com.babel.bootcamp.travels.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelController {

	void addHotel(Hotel hotel);

	void deleteHotel(String name, String city);

	void updateHotel(String name, String city, Hotel hotel);

	Hotel getHotel(String name, String city);

	List<Hotel> getHotels();
}
