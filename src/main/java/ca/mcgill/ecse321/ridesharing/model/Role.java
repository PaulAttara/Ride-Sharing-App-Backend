package ca.mcgill.ecse321.ridesharing.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(Role.class)
public abstract class Role implements Serializable{
	private User user;

	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private SystemAdministrator systemAdministrator;

	@ManyToOne(optional = false)
	public SystemAdministrator getSystemAdministrator() {
		return this.systemAdministrator;
	}

	public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
		this.systemAdministrator = systemAdministrator;
	}

	private int numOfPastTrips;

	public void setNumOfPastTrips(int value) {
		this.numOfPastTrips = value;
	}
	@Id
	public int getNumOfPastTrips() {
		return this.numOfPastTrips;
	}

	private double avgRating;

	public void setAvgRating(double value) {
		this.avgRating = value;
	}
	@Id
	public double getAvgRating() {
		return this.avgRating;
	}
}
