package com.babel.bootcamp.travels.services.impl;

import com.babel.bootcamp.travels.dao.HotelDao;
import com.babel.bootcamp.travels.model.Hotel;
import com.babel.bootcamp.travels.services.HotelService;
import com.babel.bootcamp.travels.services.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

	private HotelDao hotelDao;

	public HotelServiceImpl(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}

	@Override
	public void addHotel(Hotel hotel) {
		hotelDao.addHotel(hotel);
	}

	@Override
	public void deleteHotel(String name, String city) {
		hotelDao.deleteHotel(name, city);
	}

	@Override
	public void updateHotel(String name, String city, Hotel hotel) {
		Optional<Hotel> storedHotel = getHotel(name, city);
		if (storedHotel.isEmpty()) {
			throw new NotFoundException("Hotel not found");
		}
		hotel.setCity(city);
		hotel.setName(name);

		hotelDao.updateHotel(hotel);
	}

	@Override
	public Optional<Hotel> getHotel(String name, String city) {
		return hotelDao.getHotel(name, city);
	}

	@Override
	public List<Hotel> getHotelsFromCity(String city) {
		return hotelDao.getHotelsFromCity(city);
	}


	@Override
	public List<Hotel> getHotels() {
		return hotelDao.getHotels();
	}
}
