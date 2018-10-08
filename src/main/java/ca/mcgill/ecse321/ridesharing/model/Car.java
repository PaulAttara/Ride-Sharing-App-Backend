package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Car implements Serializable{
private SystemAdministrator systemAdministrator;

@ManyToOne(optional=false)
public SystemAdministrator getSystemAdministrator() {
   return this.systemAdministrator;
}

public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
   this.systemAdministrator = systemAdministrator;
}

private Driver driver;

@ManyToOne(optional=false)
public Driver getDriver() {
   return this.driver;
}

public void setDriver(Driver driver) {
   this.driver = driver;
}

private String licensePlate;

public void setLicensePlate(String value) {
this.licensePlate = value;
    }
@Id
public String getLicensePlate() {
return this.licensePlate;
    }
private String model;

public void setModel(String value) {
this.model = value;
    }
@Id
public String getModel() {
return this.model;
    }
private String brand;

public void setBrand(String value) {
this.brand = value;
    }
@Id
public String getBrand() {
return this.brand;
    }
private Set<Route> route;

@OneToMany(mappedBy="car")
public Set<Route> getRoute() {
   return this.route;
}

public void setRoute(Set<Route> routes) {
   this.route = routes;
}

}
