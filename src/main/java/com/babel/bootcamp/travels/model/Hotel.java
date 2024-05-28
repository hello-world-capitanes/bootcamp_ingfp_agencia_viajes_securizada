package com.babel.bootcamp.travels.model;

public class Hotel {
	private String name;
	private String city;
	private int stars;

	public Hotel(String name, String city, int stars) {
		this.name = name;
		this.city = city;
		this.stars = stars;
	}

	public Hotel() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	@Override
	public String toString() {
		return "Hotel{" + "name='" + name + '\'' + ", city='" + city + '\'' + ", stars=" + stars + '}';
	}
}
