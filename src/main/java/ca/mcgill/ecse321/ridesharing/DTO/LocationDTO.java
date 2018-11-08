package ca.mcgill.ecse321.ridesharing.DTO;

import ca.mcgill.ecse321.ridesharing.model.*;

public class LocationDTO {
	private int locationId;
	private String city, street;
	private User user;
	private Route route;
	private double price;
	
	public LocationDTO() {}
	
	public LocationDTO(int locationId, String city, String street, User user, Route route, double price) {
		this.locationId = locationId;
		this.city = city;
		this.street = street;
		this.user = user;
		this.route = route;
		this.price = price;
	}

	public int getLocationId() {
		return locationId;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public User getUser() {
		return user;
	}

	public Route getRoute() {
		return route;
	}

	public double getPrice() {
		return price;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
