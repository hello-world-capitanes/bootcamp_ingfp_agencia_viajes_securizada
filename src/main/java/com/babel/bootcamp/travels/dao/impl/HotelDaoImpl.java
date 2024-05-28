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
	public void addHotel(String name, String city, int stars) {
		String sql = "INSERT INTO hotels (name, city, stars) VALUES (:name, :city, :stars)";
		jdbcTemplate.update(sql, Map.of("name", name, "city", city, "stars", stars));
	}

	@Override
	public void deleteHotel(String name, String city) {
		String sql = "DELETE FROM hotels WHERE name = :name AND city = :city";
		jdbcTemplate.update(sql, Map.of("name", name));
	}

	@Override
	public void updateHotel(String name, String city, int stars) {
		String sql = "UPDATE hotels SET stars = :stars WHERE name = :name AND city = :city";
		jdbcTemplate.update(sql, Map.of("name", name, "city", city, "stars", stars));
	}

	@Override
	public Optional<Hotel> getHotel(String name, String city) {
		String sql = "SELECT * FROM hotels WHERE name = :name AND city = :city";
		try {
			Hotel h = jdbcTemplate.queryForObject(sql, Map.of("name", name, "city", city), (rs, rowNum) -> {
				return new Hotel(rs.getString("name"), rs.getString("city"), rs.getInt("stars"));
			});
			return Optional.of(h);
		}catch(EmptyResultDataAccessException e) { //No existe el hotel
			return Optional.empty();
		}
	}

	@Override
	public List<Hotel> getHotelsFromCity(String city) {
		String sql = "SELECT * FROM hotels WHERE city = :city";
		return jdbcTemplate.query(sql, Map.of("city", city), (rs, rowNum) -> {
			return new Hotel(rs.getString("name"), rs.getString("city"), rs.getInt("stars"));
		});
	}
}
