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
	public void addTrip(String destination, int duration, int basePrice) {
		String sql = "INSERT INTO trips (destination, duration, basePrice) VALUES (:destination, :duration, :basePrice)";
		jdbcTemplate.update(sql, Map.of("destination", destination, "duration", duration, "basePrice", basePrice));
	}

	@Override
	public void deleteTrip(String destination) {
		String sql = "DELETE FROM trips WHERE destination = :destination";
		jdbcTemplate.update(sql, Map.of("destination", destination));
	}

	@Override
	public void updateTrip(String destination, int duration, int basePrice) {
		String sql = "UPDATE trips SET duration = :duration, basePrice = :basePrice WHERE destination = :destination";
		jdbcTemplate.update(sql, Map.of("destination", destination, "duration", duration, "basePrice", basePrice));
	}

	@Override
	public Optional<Trip> getTrip(String destination) {
		String sql = "SELECT * FROM trips WHERE destination = :destination";
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
		String sql = "SELECT * FROM trips ORDER BY RAND() LIMIT 1";
		return jdbcTemplate.queryForObject(sql, EmptySqlParameterSource.INSTANCE, (rs, rowNum) ->
			new Trip(rs.getString("destination"), rs.getInt("duration"), rs.getInt("basePrice"))
		);
	}

	@Override
	public List<Trip> getTrips() {
		String sql = "SELECT * FROM trips";
		return jdbcTemplate.query(sql, (rs, rowNum) ->
			new Trip(rs.getString("destination"), rs.getInt("duration"), rs.getInt("basePrice"))
		);
	}
}
