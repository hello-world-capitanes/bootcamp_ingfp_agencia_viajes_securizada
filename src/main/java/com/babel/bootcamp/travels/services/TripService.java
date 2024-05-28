package com.babel.bootcamp.travels.services;

import com.babel.bootcamp.travels.dao.impl.TripDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class TripService {
	private final TripDaoImpl tripDao;

	public TripService(TripDaoImpl tripDao) {
		this.tripDao = tripDao;
	}


}
