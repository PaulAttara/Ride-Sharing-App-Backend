package ca.mcgill.ecse321.ridesharing.DTO;

import java.util.List;

public class UserDTO {
	private List<Double> ratings;
	private String username, password, firstName, lastName, phoneNumber, city, address, role;
	private double avgRating;
	private int numTrips;
	
	public UserDTO() {}
	
	public UserDTO(List<Double> ratings, String username, String password, String firstName, String lastName,
			String phoneNumber, String city, String address, String role, double avgRating, int numTrips) {
		this.ratings = ratings;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.address = address;
		this.role = role;
		this.avgRating = avgRating;
		this.numTrips = numTrips;
	}

	public List<Double> getRatings() {
		return ratings;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public String getRole() {
		return role;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public int getNumTrips() {
		return numTrips;
	}

	public void setRatings(List<Double> ratings) {
		this.ratings = ratings;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public void setNumTrips(int numTrips) {
		this.numTrips = numTrips;
	}
	
	
}
