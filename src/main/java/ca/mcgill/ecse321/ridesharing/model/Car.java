package ca.mcgill.ecse321.ridesharing.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT e FROM Car e")
})
public class Car{
	
	private int vehicleId;

	public void setVehicleId(int value) {
		this.vehicleId = value;
	}

	@Id
	@Column(name = "vehicleid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getVehicleId() {
		return this.vehicleId;
	}
	private String brand;

	public void setBrand(String value) {
		this.brand = value;
	}
	
	@Column(name = "brand")
	public String getBrand() {
		return this.brand;
	}
	private String model;

	public void setModel(String value) {
		this.model = value;
	}
	
	@Column(name = "model")
	public String getModel() {
		return this.model;
	}
	private String licensePlate;

	public void setLicensePlate(String value) {
		this.licensePlate = value;
	}
	
	@Column(name = "licenseplate")
	public String getLicensePlate() {
		return this.licensePlate;
	}
	private User driver;

	@OneToOne(optional=true)
//	@JsonManagedReference
	public User getDriver() {
		return this.driver;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	private Set<Route> route;

//	@OneToMany(mappedBy="car")
//	@JsonBackReference
	@Transient
	public Set<Route> getRoute() {
		return this.route;
	}

	public void setRoute(Set<Route> route) {
		this.route = route;
	}

}