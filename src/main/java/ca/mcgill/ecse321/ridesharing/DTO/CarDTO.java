package ca.mcgill.ecse321.ridesharing.DTO;

import ca.mcgill.ecse321.ridesharing.model.*;

public class CarDTO {
	private int vehicleId;
	private String brand, model, licensePlate;
	private User user;
	
	public CarDTO() {}
	
	public CarDTO(int vehicleId, String brand, String model, String licensePlate, User user) {
		this.vehicleId = vehicleId;
		this.brand = brand;
		this.model = model;
		this.licensePlate = licensePlate;
		this.user = user;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public User getUser() {
		return user;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
