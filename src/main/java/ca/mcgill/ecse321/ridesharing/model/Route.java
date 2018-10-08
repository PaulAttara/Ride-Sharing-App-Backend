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
public class Route implements Serializable{
private Status status;
   
   public void setStatus(Status value) {
this.status = value;
    }
public Status getStatus() {
return this.status;
    }
private double price;

public void setPrice(double value) {
this.price = value;
    }
public double getPrice() {
return this.price;
    }
	private SystemAdministrator systemAdministrator;

	@ManyToOne(optional = false)
	public SystemAdministrator getSystemAdministrator() {
		return this.systemAdministrator;
	}

	public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
		this.systemAdministrator = systemAdministrator;
	}

	private SortedSet<Location> location;

	@ManyToMany
	public SortedSet<Location> getLocation() {
		return this.location;
	}

	public void setLocation(SortedSet<Location> locations) {
		this.location = locations;
	}

	private int seatsAvailable;

	public void setSeatsAvailable(int value) {
		this.seatsAvailable = value;
	}
	@Id
	public int getSeatsAvailable() {
		return this.seatsAvailable;
	}

	private Date date;

	public void setDate(Date date) {
		this.date = date;
	}
	@Id
	public Date getDate() {
		return this.date;
	}

	private String comments;

	public void setComments(String value) {
		this.comments = value;
	}
	@Id
	public String getComments() {
		return this.comments;
	}

	private Car car;

	@ManyToOne(optional = false)
	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	private Set<Request> request;

	@OneToMany(mappedBy = "route")
	public Set<Request> getRequest() {
		return this.request;
	}

	public void setRequest(Set<Request> requests) {
		this.request = requests;
	}

	private Driver driver;

	@ManyToOne(optional = false)
	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver1) {
		this.driver = driver1;
	}

}
