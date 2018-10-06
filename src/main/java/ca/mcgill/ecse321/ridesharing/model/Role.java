package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public abstract class Role{
private User user;

@ManyToOne(optional=false)
public User getUser() {
   return this.user;
}

public void setUser(User user) {
   this.user = user;
}

private SystemAdministrator systemAdministrator;

@ManyToOne(optional=false)
public SystemAdministrator getSystemAdministrator() {
   return this.systemAdministrator;
}

public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
   this.systemAdministrator = systemAdministrator;
}

private int numOfPastTrips;

public void setNumOfPastTrips(int value) {
this.numOfPastTrips = value;
    }
public int getNumOfPastTrips() {
return this.numOfPastTrips;
    }
private int numOfPastDrives;

public void setNumOfPastDrives(int value) {
this.numOfPastDrives = value;
    }
public int getNumOfPastDrives() {
return this.numOfPastDrives;
    }
private double avgRating;

public void setAvgRating(double value) {
this.avgRating = value;
    }
public double getAvgRating() {
return this.avgRating;
       }
   }
