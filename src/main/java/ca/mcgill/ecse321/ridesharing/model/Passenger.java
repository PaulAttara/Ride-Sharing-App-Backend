package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Passenger extends Role{
 private Set<Request> request;


@ManyToMany(mappedBy="passenger")
public Set<Request> getRequest() {
   return this.request;
}

public void setRequest(Set<Request> requests) {
   this.request = requests;
}

}
