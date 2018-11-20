package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT e FROM User e")
})
public class User{
	
	private List<Double> ratings;
	
	public void setRatings(List<Double> ratings) {
		this.ratings=ratings;
	}
	
	public void addRating(double rating) {
		this.ratings.add(rating);
	}
	
	@ElementCollection
	@CollectionTable(name="ratings", joinColumns=@JoinColumn(name="username"))
	@Column(name="rating")
	public List<Double> getRatings(){
		return this.ratings;
	}
	private String username;

	public void setUsername(String value) {
		this.username = value;
	}

	@Id
	@Column(name = "username")
	public String getUsername() {
		return this.username;
	}
	private String password;

	public void setPassword(String value) {
		this.password = value;
	}
	
	@Column
	public String getPassword() {
		return this.password;
	}
	private String firstName;

	public void setFirstName(String value) {
		this.firstName = value;
	}
	
	@Column
	public String getFirstName() {
		return this.firstName;
	}
	private String lastName;

	public void setLastName(String value) {
		this.lastName = value;
	}
	
	@Column
	public String getLastName() {
		return this.lastName;
	}
	private String phoneNumber;

	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}
	
	@Column
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	private String city;

	public void setCity(String value) {
		this.city = value;
	}
	
	@Column
	public String getCity() {
		return this.city;
	}
	private String address;

	public void setAddress(String value) {
		this.address = value;
	}
	
	@Column
	public String getAddress() {
		return this.address;
	}
	private double avgRating;

	public void setAvgRating(double value) {
		this.avgRating = value;
	}
	
	@Column
	public double getAvgRating() {
		return this.avgRating;
	}
	private int numTrips;

	public void setNumTrips(int value) {
		this.numTrips = value;
	}
	
	public void increaseNumTrips() {
		this.numTrips = numTrips + 1;
	}
	
	@Column
	public int getNumTrips() {
		return this.numTrips;
	}
	private String role;

	public void setRole(String value) {
		this.role = value;
	}
	
	@Column
	public String getRole() {
		return this.role;
	}
	private Car car;

//	@OneToOne(mappedBy="driver", optional=true)
//	@JsonBackReference
	@Transient
	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	private Set<Location> request;

//	@OneToOne(mappedBy="passenger", optional=true)
	@Transient
	public Set<Location> getRequest() {
		return this.request;
	}

	public void setRequest(Set<Location> request) {
		this.request = request;
	}

}
