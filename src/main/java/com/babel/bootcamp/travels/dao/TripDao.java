package com.babel.bootcamp.travels.dao;

import com.babel.bootcamp.travels.model.Trip;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class TripDao {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public TripDao(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addTrip(String destination, int duration, int basePrice) {
		String sql = "INSERT INTO trips (destination, duration, basePrice) VALUES (:destination, :duration, :basePrice)";
		jdbcTemplate.update(sql, Map.of("destination", destination, "duration", duration, "basePrice", basePrice));
	}

	public void deleteTrip(String destination) {
		String sql = "DELETE FROM trips WHERE destination = :destination";
		jdbcTemplate.update(sql, Map.of("destination", destination));
	}

	public void updateTrip(String destination, int duration, int basePrice) {
		String sql = "UPDATE trips SET duration = :duration, basePrice = :basePrice WHERE destination = :destination";
		jdbcTemplate.update(sql, Map.of("destination", destination, "duration", duration, "basePrice", basePrice));
	}

	public Optional<Trip> getTrip(String destination) {
		String sql = "SELECT * FROM trips WHERE destination = :destination";
		try {
			Trip t = jdbcTemplate.queryForObject(sql, Map.of("destination", destination), (rs, rowNum) -> {
				return new Trip(rs.getString("destination"), rs.getInt("duration"), rs.getInt("basePrice"));
			});
			return Optional.of(t);
		}catch(EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public Trip getRandomTrip() {
		String sql = "SELECT * FROM trips ORDER BY RAND() LIMIT 1";
		return jdbcTemplate.queryForObject(sql, EmptySqlParameterSource.INSTANCE, (rs, rowNum) -> {
			return new Trip(rs.getString("destination"), rs.getInt("duration"), rs.getInt("basePrice"));
		});
	}
}
