package com.babel.bootcamp.travels.dao.impl;

import com.babel.bootcamp.travels.model.Trip;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TripDaoImpl implements com.babel.bootcamp.travels.dao.TripDao {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public TripDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addTrip(Trip trip) {
		String sql = "INSERT INTO TRIP (destination, duration, basePrice) VALUES (:destination, :duration, :basePrice)";
		jdbcTemplate.update(sql, Map.of("destination", trip.getDestination(), "duration", trip.getDuration(), "basePrice", trip.getBasePrice()));
	}

	@Override
	public void deleteTrip(String destination) {
		String sql = "DELETE FROM TRIP WHERE destination = :destination";
		jdbcTemplate.update(sql, Map.of("destination", destination));
	}

	@Override
	public void updateTrip(Trip trip) {
		String sql = "UPDATE TRIP SET duration = :duration, basePrice = :basePrice WHERE destination = :destination";
		jdbcTemplate.update(sql, Map.of("destination", trip.getDestination(), "duration", trip.getDuration(), "basePrice", trip.getBasePrice()));
	}

	@Override
	public Optional<Trip> getTrip(String destination) {
		String sql = "SELECT destination, duration, basePrice FROM TRIP WHERE destination = :destination";
		try {
			Trip t = jdbcTemplate.queryForObject(sql, Map.of("destination", destination), (rs, rowNum) ->
				new Trip(rs.getString("destination"), rs.getInt("duration"), rs.getInt("basePrice"))
			);
			return Optional.of(t);
		}catch(EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Trip getRandomTrip() {
		String sql = "SELECT destination, duration, basePrice FROM TRIP ORDER BY dbms_random.value() FETCH FIRST ROW ONLY";
		return jdbcTemplate.queryForObject(sql, EmptySqlParameterSource.INSTANCE, (rs, rowNum) ->
			new Trip(rs.getString("destination"), rs.getInt("duration"), rs.getInt("basePrice"))
		);
	}

	@Override
	public List<Trip> getTrips() {
		String sql = "SELECT destination, duration, basePrice FROM TRIP";
		return jdbcTemplate.query(sql, (rs, rowNum) ->
			new Trip(rs.getString("destination"), rs.getInt("duration"), rs.getInt("basePrice"))
		);
	}
}
