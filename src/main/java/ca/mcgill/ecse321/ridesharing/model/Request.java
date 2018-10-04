package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Request{
 private SystemAdministrator systemAdministrator;


@ManyToOne(optional=false)
public SystemAdministrator getSystemAdministrator() {
   return this.systemAdministrator;
}

public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
   this.systemAdministrator = systemAdministrator;
}

 private Status status;

public void setStatus(Status value) {
this.status = value;
    }

public Status getStatus() {
return this.status;
    }
 private Set<Location> pickUp;


@ManyToMany(mappedBy="request")
public Set<Location> getPickUp() {
   return this.pickUp;
}

public void setPickUp(Set<Location> pickUps) {
   this.pickUp = pickUps;
}

 private Set<Location> dropOff;


@ManyToMany(mappedBy="request1")
public Set<Location> getDropOff() {
   return this.dropOff;
}

public void setDropOff(Set<Location> dropOffs) {
   this.dropOff = dropOffs;
}

 private Set<Passenger> passenger;


@ManyToMany
public Set<Passenger> getPassenger() {
   return this.passenger;
}

public void setPassenger(Set<Passenger> passengers) {
   this.passenger = passengers;
}

 private Rating rating;

public void setRating(Rating value) {
this.rating = value;
    }


public Rating getRating() {
return this.rating;
       }
   }
