package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Car{
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

}
