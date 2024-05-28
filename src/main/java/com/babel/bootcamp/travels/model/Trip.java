package com.babel.bootcamp.travels.model;

public class Trip {
	private String destination;
	private int duration;
	private int basePrice;

	public Trip(String destination, int duration, int basePrice) {
		this.destination = destination;
		this.duration = duration;
		this.basePrice = basePrice;
	}

	public Trip() {
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int baseprice) {
		this.basePrice = baseprice;
	}

}
