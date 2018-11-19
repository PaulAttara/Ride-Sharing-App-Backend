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
	private Status status;
	
	public RouteDTO() {}
	
	public RouteDTO(int routeId, int seatsAvailable, String startLocation, Timestamp date, Car car,
			Set<Location> locations, Status status) {
		this.routeId = routeId;
		this.seatsAvailable = seatsAvailable;
		this.startLocation = startLocation;
		this.date = date;
		this.car = car;
		this.locations = locations;
		this.status = status;
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
	public Status getStatus() {
		return status;
	}
	public Set<Location> getLocations() {
		return locations;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public void setStatus(Status status) {
		this.status = status;
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
