package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class User{
private Set<Role> role;

@OneToMany(mappedBy="user")
public Set<Role> getRole() {
   return this.role;
}

public void setRole(Set<Role> roles) {
   this.role = roles;
}

private SystemAdministrator systemAdministrator;

@ManyToOne(optional=false)
public SystemAdministrator getSystemAdministrator() {
   return this.systemAdministrator;
}

public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
   this.systemAdministrator = systemAdministrator;
}

}
