package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Location{
 private Set<Request> request;


@ManyToMany
@Id
public Set<Request> getRequest() {
   return this.request;
}

public void setRequest(Set<Request> requests) {
   this.request = requests;
}

 private Set<Request> request1;


@ManyToMany
@Id
public Set<Request> getRequest1() {
   return this.request1;
}

public void setRequest1(Set<Request> request1s) {
   this.request1 = request1s;
}

 private SystemAdministrator systemAdministrator;


@ManyToOne(optional=false)
@Id
public SystemAdministrator getSystemAdministrator() {
   return this.systemAdministrator;
}

public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
   this.systemAdministrator = systemAdministrator;
}

 private Set<Route> route;


@ManyToMany(mappedBy="location")
@Id
public Set<Route> getRoute() {
   return this.route;
}

public void setRoute(Set<Route> routes) {
   this.route = routes;
}

}