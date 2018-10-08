package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Route implements Serializable {
	@Id
	private Status status;

	public void setStatus(Status value) {
		this.status = value;
	}

	public Status getStatus() {
		return this.status;
	}

	@Id
	private double price;

	public void setPrice(double value) {
		this.price = value;
	}

	public double getPrice() {
		return this.price;
	}

	@Id
	private SystemAdministrator systemAdministrator;

	@ManyToOne(optional = false)
	public SystemAdministrator getSystemAdministrator() {
		return this.systemAdministrator;
	}

	public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
		this.systemAdministrator = systemAdministrator;
	}

	@Id
	private SortedSet<Location> location;

	@ManyToMany
	public SortedSet<Location> getLocation() {
		return this.location;
	}

	public void setLocation(SortedSet<Location> locations) {
		this.location = locations;
	}

	@Id
	private int seatsAvailable;

	public void setSeatsAvailable(int value) {
		this.seatsAvailable = value;
	}

	public int getSeatsAvailable() {
		return this.seatsAvailable;
	}

	@Id
	private Date date;

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return this.date;
	}

	@Id
	private String comments;

	public void setComments(String value) {
		this.comments = value;
	}

	public String getComments() {
		return this.comments;
	}

	@Id
	private Car car;

	@ManyToOne(optional = false)
	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Id
	private Set<Request> request;

	@OneToMany(mappedBy = "route")
	public Set<Request> getRequest() {
		return this.request;
	}

	public void setRequest(Set<Request> requests) {
		this.request = requests;
	}

	@Id
	private Driver driver;

	@ManyToOne(optional = false)
	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver1) {
		this.driver = driver1;
	}

}
