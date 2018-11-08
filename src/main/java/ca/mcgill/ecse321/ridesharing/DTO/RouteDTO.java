package ca.mcgill.ecse321.ridesharing.DTO;

import java.sql.Timestamp;
import java.util.Set;

import ca.mcgill.ecse321.ridesharing.model.*;

public class RouteDTO {
	private int routeId, seatsAvailable;
	private String startLocation;
	private Timestamp date;
	private Car car;
	private Set<Location> locations;
	
	public RouteDTO() {}
	
	public RouteDTO(int routeId, int seatsAvailable, String startLocation, Timestamp date, Car car,
			Set<Location> locations) {
		this.routeId = routeId;
		this.seatsAvailable = seatsAvailable;
		this.startLocation = startLocation;
		this.date = date;
		this.car = car;
		this.locations = locations;
	}
	
	public int getRouteId() {
		return routeId;
	}
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public String getStartLocation() {
		return startLocation;
	}
	public Timestamp getDate() {
		return date;
	}
	public Car getCar() {
		return car;
	}
	public Set<Location> getLocations() {
		return locations;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}
	
}
