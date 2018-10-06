package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class SystemAdministrator{
private Set<Request> request;

@OneToMany(mappedBy="systemAdministrator", cascade={CascadeType.ALL})
public Set<Request> getRequest() {
   return this.request;
}

public void setRequest(Set<Request> requests) {
   this.request = requests;
}

private Set<Car> car;

@OneToMany(mappedBy="systemAdministrator", cascade={CascadeType.ALL})
public Set<Car> getCar() {
   return this.car;
}

public void setCar(Set<Car> cars) {
   this.car = cars;
}

private Set<Route> route;

@OneToMany(mappedBy="systemAdministrator", cascade={CascadeType.ALL})
public Set<Route> getRoute() {
   return this.route;
}

public void setRoute(Set<Route> routes) {
   this.route = routes;
}

private Set<Location> location;

@OneToMany(mappedBy="systemAdministrator", cascade={CascadeType.ALL})
public Set<Location> getLocation() {
   return this.location;
}

public void setLocation(Set<Location> locations) {
   this.location = locations;
}

private Set<User> user;

@OneToMany(mappedBy="systemAdministrator", cascade={CascadeType.ALL})
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

private Set<Role> role;

@OneToMany(mappedBy="systemAdministrator", cascade={CascadeType.ALL})
public Set<Role> getRole() {
   return this.role;
}

public void setRole(Set<Role> roles) {
   this.role = roles;
}

private String adminUsername;

public void setAdminUsername(String value) {
this.adminUsername = value;
    }
public String getAdminUsername() {
return this.adminUsername;
    }
private String password;

public void setPassword(String value) {
this.password = value;
    }
public String getPassword() {
return this.password;
       }
   }
