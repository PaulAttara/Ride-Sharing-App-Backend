package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
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

private String userName;

public void setUserName(String value) {
this.userName = value;
    }
@Id
public String getUserName() {
return this.userName;
    }
private String password;

public void setPassword(String value) {
this.password = value;
    }
@Id
public String getPassword() {
return this.password;
    }
private String firstName;

public void setFirstName(String value) {
this.firstName = value;
    }
@Id
public String getFirstName() {
return this.firstName;
    }
private String lastName;

public void setLastName(String value) {
this.lastName = value;
    }
@Id
public String getLastName() {
return this.lastName;
    }
private String city;

public void setCity(String value) {
this.city = value;
    }
@Id
public String getCity() {
return this.city;
    }
private String phoneNumber;

public void setPhoneNumber(String value) {
this.phoneNumber = value;
    }
@Id
public String getPhoneNumber() {
return this.phoneNumber;
    }
private String address;

public void setAddress(String value) {
this.address = value;
    }
@Id
public String getAddress() {
return this.address;
       }
   }
