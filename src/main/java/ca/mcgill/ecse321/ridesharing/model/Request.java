package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Request implements Serializable {
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
	private Status status;

	public void setStatus(Status value) {
		this.status = value;
	}

	public Status getStatus() {
		return this.status;
	}

	@Id
	private Location pickUp;

	@ManyToOne(optional = false)
	public Location getPickUp() {
		return this.pickUp;
	}

	public void setPickUp(Location pickUp) {
		this.pickUp = pickUp;
	}

	@Id
	private Location dropOff;

	@ManyToOne(optional = false)
	public Location getDropOff() {
		return this.dropOff;
	}

	public void setDropOff(Location dropOff) {
		this.dropOff = dropOff;
	}

	@Id
	private Passenger passenger;

	@ManyToOne(optional = false)
	public Passenger getPassenger() {
		return this.passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	@Id
	private Route route;

	@ManyToOne(optional = false)
	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}
