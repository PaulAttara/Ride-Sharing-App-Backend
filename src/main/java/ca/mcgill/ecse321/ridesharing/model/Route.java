package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="routes")
@NamedQueries({
	@NamedQuery(name = "Route.findAll", query = "SELECT e FROM Route e")
})
public class Route{
	private int routeId;

	public void setRouteId(int value) {
		this.routeId = value;
	}

	@Id
	@Column(name="routeid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getRouteId() {
		return this.routeId;
	}
	private int seatsAvailable;

	public void setSeatsAvailable(int value) {
		this.seatsAvailable = value;
	}
	
	@Column
	public int getSeatsAvailable() {
		return this.seatsAvailable;
	}
	
	private String startLocation;
	
	public void setStartLocation(String value) {
		this.startLocation = value;
	}
	
	@Column
	public String getStartLocation() {
		return this.startLocation;
	}
	
	private Timestamp date;
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	@Column
	public Timestamp getDate() {
		return this.date;
	}
	
	private Car car;

	@ManyToOne(optional=false)
//	@JsonManagedReference
	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	private Set<Location> stops;

//	@OneToMany(mappedBy="route")
	@Transient
	public Set<Location> getStops() {
		return this.stops;
	}

	public void setStops(Set<Location> locations) {
		this.stops = locations;
	}

}
