package com.babel.bootcamp.travels.model;


public class TripOffer {
	private Trip trip;
	private Hotel hotel;

	public TripOffer(Trip trip, Hotel hotel) {
		this.trip = trip;
		this.hotel = hotel;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public double getPrice() {
		return switch (hotel.getStars()) {
			case 1 -> trip.getBasePrice();
			case 2 -> trip.getBasePrice() * 1.2;
			case 3 -> trip.getBasePrice() * 1.5;
			case 4 -> trip.getBasePrice() * 2;
			case 5 -> trip.getBasePrice() * 3;
			default -> trip.getBasePrice() * 5;
		};
	}

	@Override
	public String toString() {
		return "TripOffer{" + "trip=" + trip + ", hotel=" + hotel + '}';
	}
}
