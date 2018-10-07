package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Request implements Serializable {
	private SystemAdministrator systemAdministrator;

	@ManyToOne(optional = false)
	public SystemAdministrator getSystemAdministrator() {
		return this.systemAdministrator;
	}

	public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
		this.systemAdministrator = systemAdministrator;
	}

	private Status status;

	public void setStatus(Status value) {
		this.status = value;
	}

	@Id
	public Status getStatus() {
		return this.status;
	}

	private Set<Location> pickUp;

	@ManyToMany(mappedBy = "request")
	public Set<Location> getPickUp() {
		return this.pickUp;
	}

	public void setPickUp(Set<Location> pickUps) {
		this.pickUp = pickUps;
	}

	private Set<Location> dropOff;

	@ManyToMany(mappedBy = "request1")
	public Set<Location> getDropOff() {
		return this.dropOff;
	}

	public void setDropOff(Set<Location> dropOffs) {
		this.dropOff = dropOffs;
	}

	private Passenger passenger;

	@ManyToOne(optional = false)
	public Passenger getPassenger() {
		return this.passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	private Route route;

	@ManyToOne(optional = false)
	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}
