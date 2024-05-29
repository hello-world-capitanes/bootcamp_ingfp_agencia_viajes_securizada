package com.babel.bootcamp.travels.dao.impl;

import com.babel.bootcamp.travels.model.Hotel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class HotelDaoImpl implements com.babel.bootcamp.travels.dao.HotelDao {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public HotelDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addHotel(Hotel hotel) {
		String sql = "INSERT INTO HOTEL (name, city, stars) VALUES (:name, :city, :stars)";
		jdbcTemplate.update(sql, Map.of("name", hotel.getName(), "city", hotel.getCity(), "stars", hotel.getStars()));
	}

	@Override
	public void deleteHotel(String name, String city) {
		String sql = "DELETE FROM HOTEL WHERE name = :name AND city = :city";
		jdbcTemplate.update(sql, Map.of("name", name));
	}

	@Override
	public void updateHotel(Hotel hotel) {
		String sql = "UPDATE HOTEL SET stars = :stars WHERE name = :name AND city = :city";
		jdbcTemplate.update(sql, Map.of("name", hotel.getName(), "city", hotel.getCity(), "stars", hotel.getStars()));
	}

	@Override
	public Optional<Hotel> getHotel(String name, String city) {
		String sql = "SELECT name, city, stars FROM HOTEL WHERE name = :name AND city = :city";
		try {
			Hotel h = jdbcTemplate.queryForObject(sql, Map.of("name", name, "city", city), (rs, rowNum) ->
				new Hotel(rs.getString("name"), rs.getString("city"), rs.getInt("stars"))
			);
			return Optional.of(h);
		}catch(EmptyResultDataAccessException e) { //No existe el hotel
			return Optional.empty();
		}
	}

	@Override
	public List<Hotel> getHotelsFromCity(String city) {
		String sql = "SELECT name, city, stars FROM HOTEL WHERE city = :city";
		return jdbcTemplate.query(sql, Map.of("city", city), (rs, rowNum) ->
			new Hotel(rs.getString("name"), rs.getString("city"), rs.getInt("stars"))
		);
	}

	@Override
	public List<Hotel> getHotels() {
		String sql = "SELECT name, city, stars FROM HOTEL";
		return jdbcTemplate.query(sql, (rs, rowNum) ->
			new Hotel(rs.getString("name"), rs.getString("city"), rs.getInt("stars"))
		);
	}
}
