package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class Role{
 private User user;


@ManyToOne(optional=false)
@Id
public User getUser() {
   return this.user;
}

public void setUser(User user) {
   this.user = user;
}

 private SystemAdministrator systemAdministrator;


@ManyToOne(optional=false)
@Id
public SystemAdministrator getSystemAdministrator() {
   return this.systemAdministrator;
}

public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
   this.systemAdministrator = systemAdministrator;
}

}
