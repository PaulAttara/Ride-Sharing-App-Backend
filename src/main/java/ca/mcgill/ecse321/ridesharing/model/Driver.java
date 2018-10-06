package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Driver extends Role{
private Set<Route> route;

@ManyToMany(mappedBy="driver")
public Set<Route> getRoute() {
   return this.route;
}

public void setRoute(Set<Route> routes) {
   this.route = routes;
}

private Set<Car> car;

@OneToMany(mappedBy="driver")
public Set<Car> getCar() {
   return this.car;
}

public void setCar(Set<Car> cars) {
   this.car = cars;
}

private Set<Route> route1;

@OneToMany(mappedBy="driver1")
public Set<Route> getRoute1() {
   return this.route1;
}

public void setRoute1(Set<Route> route1s) {
   this.route1 = route1s;
}

}
