package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.IdClass;

@IdClass(Role.class)
@Entity
public abstract class Role implements Serializable {
	@Id
	private User user;

	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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
	private int numOfPastTrips;

	public void setNumOfPastTrips(int value) {
		this.numOfPastTrips = value;
	}

	public int getNumOfPastTrips() {
		return this.numOfPastTrips;
	}

	@Id
	private double avgRating;

	public void setAvgRating(double value) {
		this.avgRating = value;
	}

	public double getAvgRating() {
		return this.avgRating;
	}
}
