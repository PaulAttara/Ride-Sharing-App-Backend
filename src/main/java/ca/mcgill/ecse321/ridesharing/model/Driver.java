package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.ManyToMany;

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


@ManyToMany(mappedBy="driver")
public Set<Car> getCar() {
   return this.car;
}

public void setCar(Set<Car> cars) {
   this.car = cars;
}

}
