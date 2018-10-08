package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Id;

@Entity
public class Location implements Serializable{
private Set<Request> request;

@OneToMany(mappedBy="pickUp")
public Set<Request> getRequest() {
   return this.request;
}

public void setRequest(Set<Request> requests) {
   this.request = requests;
}

private Set<Request> request1;

@OneToMany(mappedBy="dropOff")
public Set<Request> getRequest1() {
   return this.request1;
}

public void setRequest1(Set<Request> request1s) {
   this.request1 = request1s;
}

private SystemAdministrator systemAdministrator;

@ManyToOne(optional=false)
public SystemAdministrator getSystemAdministrator() {
   return this.systemAdministrator;
}

public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
   this.systemAdministrator = systemAdministrator;
}

private Set<Route> route;

@ManyToMany(mappedBy="location")
public Set<Route> getRoute() {
   return this.route;
}

public void setRoute(Set<Route> routes) {
   this.route = routes;
}

private double lattitude;

public void setLattitude(double value) {
this.lattitude = value;
    }
@Id
public double getLattitude() {
return this.lattitude;
    }
private double longitude;

public void setLongitude(double value) {
this.longitude = value;
    }
@Id
public double getLongitude() {
return this.longitude;
       }
   }
