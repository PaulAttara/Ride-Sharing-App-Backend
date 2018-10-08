package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
	@Id
	private Set<Role> role;

	@OneToMany(mappedBy = "user")
	public Set<Role> getRole() {
		return this.role;
	}

	public void setRole(Set<Role> roles) {
		this.role = roles;
	}

	@Id
	private SystemAdministrator systemAdministrator;

	@ManyToOne(optional = false)
	public SystemAdministrator getSystemAdministrator() {
		return this.systemAdministrator;
	}

	public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
		this.systemAdministrator = systemAdministrator;
	}

	@Id
	private String userName;

	public void setUserName(String value) {
		this.userName = value;
	}

	public String getUserName() {
		return this.userName;
	}

	@Id
	private String password;

	public void setPassword(String value) {
		this.password = value;
	}

	public String getPassword() {
		return this.password;
	}

	@Id
	private String firstName;

	public void setFirstName(String value) {
		this.firstName = value;
	}

	public String getFirstName() {
		return this.firstName;
	}

	@Id
	private String lastName;

	public void setLastName(String value) {
		this.lastName = value;
	}

	public String getLastName() {
		return this.lastName;
	}

	@Id
	private String city;

	public void setCity(String value) {
		this.city = value;
	}

	public String getCity() {
		return this.city;
	}

	@Id
	private String phoneNumber;

	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	@Id
	private String address;

	public void setAddress(String value) {
		this.address = value;
	}

	public String getAddress() {
		return this.address;
	}
}
