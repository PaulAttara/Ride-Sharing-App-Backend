package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Car implements Serializable {
	private SystemAdministrator systemAdministrator;

	@ManyToOne(optional = false)
	public SystemAdministrator getSystemAdministrator() {
		return this.systemAdministrator;
	}

	public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
		this.systemAdministrator = systemAdministrator;
	}

	@Id
	private Driver driver;

	@ManyToOne(optional = false)
	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Id
	private String licensePlate;

	public void setLicensePlate(String value) {
		this.licensePlate = value;
	}

	public String getLicensePlate() {
		return this.licensePlate;
	}

	@Id
	private String model;

	public void setModel(String value) {
		this.model = value;
	}

	public String getModel() {
		return this.model;
	}

	@Id
	private String brand;

	public void setBrand(String value) {
		this.brand = value;
	}

	public String getBrand() {
		return this.brand;
	}

	@Id
	private Set<Route> route;

	@OneToMany(mappedBy = "car")
	public Set<Route> getRoute() {
		return this.route;
	}

	public void setRoute(Set<Route> routes) {
		this.route = routes;
	}

}
