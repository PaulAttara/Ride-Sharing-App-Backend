package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Route{
private SystemAdministrator systemAdministrator;

@ManyToOne(optional=false)
public SystemAdministrator getSystemAdministrator() {
   return this.systemAdministrator;
}

public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
   this.systemAdministrator = systemAdministrator;
}

private Set<Driver> driver;

@ManyToMany
public Set<Driver> getDriver() {
   return this.driver;
}

public void setDriver(Set<Driver> drivers) {
   this.driver = drivers;
}

private Set<Location> location;

@ManyToMany
public Set<Location> getLocation() {
   return this.location;
}

public void setLocation(Set<Location> locations) {
   this.location = locations;
}

private int seatsAvailable;

public void setSeatsAvailable(int value) {
this.seatsAvailable = value;
    }
public int getSeatsAvailable() {
return this.seatsAvailable;
    }
private String date;

public void setDate(String value) {
this.date = value;
    }
public String getDate() {
return this.date;
    }
private String comments;

public void setComments(String value) {
this.comments = value;
    }
public String getComments() {
return this.comments;
    }
private Car car;

@ManyToOne(optional=false)
public Car getCar() {
   return this.car;
}

public void setCar(Car car) {
   this.car = car;
}

private Set<Request> request;

@OneToMany(mappedBy="route")
public Set<Request> getRequest() {
   return this.request;
}

public void setRequest(Set<Request> requests) {
   this.request = requests;
}

private Driver driver1;

@ManyToOne(optional=false)
public Driver getDriver1() {
   return this.driver1;
}

public void setDriver1(Driver driver1) {
   this.driver1 = driver1;
}

}
