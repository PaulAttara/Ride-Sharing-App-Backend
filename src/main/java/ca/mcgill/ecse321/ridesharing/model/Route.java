package ca.mcgill.ecse321.ridesharing.model;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Route{
/**
 * <pre>
 *           0..*     1..1
 * Location ------------------------- SystemAdministrator
 *           location        &lt;       systemAdministrator
 * </pre>
 */
private SystemAdministrator systemAdministrator;

public SystemAdministrator getSystemAdministrator() {
   return this.systemAdministrator;
}

public void setSystemAdministrator(SystemAdministrator value) {
   this.systemAdministrator = value;
}


 private Set<Driver> driver;


@ManyToMany
public Set<Driver> getDriver() {
   return this.driver;
}

public void setDriver(Set<Driver> drivers) {
   this.driver = drivers;
}
/**
 * <pre>
 *           1..1     0..*
 * SystemAdministrator ------------------------- Location
 *           systemAdministrator        &gt;       location
 * </pre>
 */
private Set<Location> location;

public Set<Location> getLocation() {
   if (this.location == null) {
this.location = new HashSet<Location>();
   }
   return this.location;
}


}
