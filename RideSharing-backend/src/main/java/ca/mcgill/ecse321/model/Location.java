package ca.mcgill.ecse321.model;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@NamedQueries({
	@NamedQuery(name = "Location.findAll", query = "SELECT e FROM Location e")
})
public class Location{
	private int locationId;

	public void setLocationId(int value) {
		this.locationId = value;
	}

	@Id
	@Column(name="locationid")
	public int getLocationId() {
		return this.locationId;
	}
	private String city;

	public void setCity(String value) {
		this.city = value;
	}
	
	@Column
	public String getCity() {
		return this.city;
	}
	private String street;

	public void setStreet(String value) {
		this.street = value;
	}
	
	@Column
	public String getStreet() {
		return this.street;
	}
	private Route route;

	@ManyToOne(optional=false)
	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	private User passenger;

	@OneToOne(optional=false)
	public User getPassenger() {
		return this.passenger;
	}

	public void setPassenger(User passenger) {
		this.passenger = passenger;
	}

	private double price;

	public void setPrice(double value) {
		this.price = value;
	}
	
	@Column
	public double getPrice() {
		return this.price;
	}
}
