package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Car{
/**
 * <pre>
 *           1..1     1..1
 * Location ------------------------> SystemAdministrator
 *           &lt;       systemAdministrator
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
@Id
public Set<Driver> getDriver() {
   return this.driver;
}

public void setDriver(Set<Driver> drivers) {
   this.driver = drivers;
}

}
