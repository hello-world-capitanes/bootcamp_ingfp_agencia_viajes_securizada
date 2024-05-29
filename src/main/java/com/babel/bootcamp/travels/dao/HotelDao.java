package com.babel.bootcamp.travels.dao;

import com.babel.bootcamp.travels.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelDao {
	void addHotel(Hotel hotel);

	void deleteHotel(String name, String city);

	void updateHotel(Hotel hotel);

	Optional<Hotel> getHotel(String name, String city);

	List<Hotel> getHotelsFromCity(String city);

	List<Hotel> getHotels();
}
